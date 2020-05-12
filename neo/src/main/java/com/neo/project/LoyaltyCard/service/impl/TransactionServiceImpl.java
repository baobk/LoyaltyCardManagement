package com.neo.project.LoyaltyCard.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neo.project.LoyaltyCard.dto.TransactionDto;
import com.neo.project.LoyaltyCard.dto.TransactionListDTO;
import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;
import com.neo.project.LoyaltyCard.exception.ServiceExceptionAdvice;
import com.neo.project.LoyaltyCard.mapper.LoyaltyCardMapper;
import com.neo.project.LoyaltyCard.mapper.LoyaltyCardTypeMapper;
import com.neo.project.LoyaltyCard.mapper.PointConfigMapper;
import com.neo.project.LoyaltyCard.pojo.LoyaltyCard;
import com.neo.project.LoyaltyCard.service.TransactionService;
import com.neo.project.LoyaltyCard.util.HttpUtil;
import com.neo.project.LoyaltyCard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Value("${remoteHost}")
    private String remoteHost;

    @Autowired
    PointConfigMapper pointConfigMapper;

    @Autowired
    LoyaltyCardMapper loyaltyCardMapper;

    @Autowired
    LoyaltyCardTypeMapper loyaltyCardTypeMapper;

    @Override
    public ResponseDTO handleTransactions(RequestDTO<TransactionListDTO> requestDto) throws ServiceExceptionAdvice {
        ResponseDTO resp = new ResponseDTO();
        Map<Integer, LoyaltyCard> loyaltyCardMap = new HashMap<>();
        int pointConfig = pointConfigMapper.selectConfig();
        if(pointConfig < 0) throw new ServiceExceptionAdvice("LCT","Config is not exits");

        List<LoyaltyCard> loyaltyCardList = loyaltyCardMapper.selectAll();
        if (StringUtils.isEmpty(loyaltyCardList.size()))
            throw new ServiceExceptionAdvice("LCT","There is no LoyaltyCard in System");

        List<TransactionDto> transactionDtoList = requestDto.getBody().getListOfTransaction();
        if (transactionDtoList.size() > 0) {

            List<Integer> list = new ArrayList<>();

            for (TransactionDto entry:transactionDtoList) {
                int id = entry.getLoyaltyCardId();
                if(list.isEmpty() || !list.contains(id)) {
                    list.add(id);
                }
            }

            List<LoyaltyCard> cardList = loyaltyCardMapper.selectbyListId(list);

            for (TransactionDto entry:transactionDtoList) {
                int id = entry.getLoyaltyCardId();
                if (loyaltyCardMap.containsKey(id)){
                    double pointAdjust = entry.getSpentAdjust()/pointConfig;
                    LoyaltyCard loyaltyCard = loyaltyCardMap.get(id);
                    loyaltyCard.setPoint(pointAdjust + loyaltyCard.getPoint());
                    loyaltyCard.setTotalSpent(entry.getSpentAdjust() + loyaltyCard.getTotalSpent());
                }
            }

            for (Map.Entry<Integer, LoyaltyCard> entry: loyaltyCardMap.entrySet()) {
                Integer id = loyaltyCardTypeMapper.selectNextLoyaltyCardType(entry.getValue().getTotalSpent());
                if (id != null) {
                    LoyaltyCard loyaltyCard = entry.getValue();
                    loyaltyCard.setLoyaltyCartTypeId(id);
                }
            }

            List<LoyaltyCard> loyaltyCards = loyaltyCardMap.values().stream().collect(Collectors.toList());

            JSONObject result = HttpUtil.sendPostDataByJson(remoteHost,10000, JSON.toJSONString(loyaltyCards));

            if (result != null){
                if(!result.getString("code").equals("00000")) {
                    throw new ServiceExceptionAdvice("LCT","Failed update loyalty card");
                }
            } else {
                throw new ServiceExceptionAdvice("LCT","There is no response result after call remote provider");
            }
        }

        return ResponseUtil.success();
    }

    @Override
    public List<LoyaltyCard> selectAll() {
        return loyaltyCardMapper.selectAll();
    }
}
