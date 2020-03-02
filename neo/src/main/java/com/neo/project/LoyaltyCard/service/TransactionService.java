package com.neo.project.LoyaltyCard.service;

import com.neo.project.LoyaltyCard.dto.TransactionDto;
import com.neo.project.LoyaltyCard.dto.TransactionListDTO;
import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;
import com.neo.project.LoyaltyCard.exception.ServiceExceptionAdvice;
import com.neo.project.LoyaltyCard.pojo.LoyaltyCard;

import java.util.List;

public interface TransactionService {
    ResponseDTO handleTransactions(RequestDTO<TransactionListDTO> requestDto) throws ServiceExceptionAdvice;
    List<LoyaltyCard> selectAll();
}
