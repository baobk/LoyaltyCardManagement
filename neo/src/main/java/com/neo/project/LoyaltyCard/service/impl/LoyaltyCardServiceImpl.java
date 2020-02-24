package com.neo.project.LoyaltyCard.service.impl;

import com.neo.project.LoyaltyCard.dto.TransactionDto;
import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;
import com.neo.project.LoyaltyCard.exception.ServiceExceptionAdvice;
import com.neo.project.LoyaltyCard.mapper.LoyaltyCardMapper;
import com.neo.project.LoyaltyCard.mapper.LoyaltyCardTypeMapper;
import com.neo.project.LoyaltyCard.mapper.PointConfigMapper;
import com.neo.project.LoyaltyCard.pojo.LoyaltyCard;
import com.neo.project.LoyaltyCard.service.LoyaltyCardService;
import com.neo.project.LoyaltyCard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoyaltyCardServiceImpl implements LoyaltyCardService {

    @Autowired
    PointConfigMapper pointConfigMapper;

    @Autowired
    LoyaltyCardMapper loyaltyCardMapper;

    @Autowired
    LoyaltyCardTypeMapper loyaltyCardTypeMapper;

    @Override
    public ResponseDTO updateLoyaltyCard(RequestDTO<List<TransactionDto>> requestDto) throws ServiceExceptionAdvice {
        ResponseDTO resp = new ResponseDTO();
        Map<Integer, LoyaltyCard> loyaltyCardMap = new HashMap<>();
        int pointConfig = pointConfigMapper.selectConfig();
        if(pointConfig < 0) throw new ServiceExceptionAdvice("2222","Config is not exits");

        List<LoyaltyCard> loyaltyCardList = loyaltyCardMapper.selectAll();
        if (StringUtils.isEmpty(loyaltyCardList.size()))
            throw new ServiceExceptionAdvice("3333","There is no LoyaltyCard in System");

        List<TransactionDto> transactionDtoList = requestDto.getBody();
        if (transactionDtoList.size() > 0) {

            for (TransactionDto entry:transactionDtoList) {
                LoyaltyCard loyaltyCard = loyaltyCardMapper.selectById(entry.getLoyaltyCardId());
                if(loyaltyCard != null) {
                    loyaltyCardMap.put(entry.getLoyaltyCardId(),loyaltyCard);
                }
            }

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
        }

        return ResponseUtil.success();
    }

    @Override
    public List<LoyaltyCard> selectAll() {
        return loyaltyCardMapper.selectAll();
    }
}
