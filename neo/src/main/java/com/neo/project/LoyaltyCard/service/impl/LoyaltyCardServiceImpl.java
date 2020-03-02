package com.neo.project.LoyaltyCard.service.impl;

import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;
import com.neo.project.LoyaltyCard.exception.ServiceExceptionAdvice;
import com.neo.project.LoyaltyCard.pojo.LoyaltyCard;
import com.neo.project.LoyaltyCard.respository.LoyaltyCardRepository;
import com.neo.project.LoyaltyCard.service.LoyaltyCardService;
import com.neo.project.LoyaltyCard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LoyaltyCardServiceImpl implements LoyaltyCardService {

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @Autowired
    private LoyaltyCardRepository loyaltyCardRepository;

    @Override
    public ResponseDTO updateLoyaltyCard(RequestDTO<List<LoyaltyCard>> requestDTO) throws ServiceExceptionAdvice {
        List<LoyaltyCard> loyaltyCards = requestDTO.getBody();
        loyaltyCardRepository.saveAll(loyaltyCards);
        return ResponseUtil.success();
    }
}
