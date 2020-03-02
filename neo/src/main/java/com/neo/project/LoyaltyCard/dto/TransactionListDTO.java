package com.neo.project.LoyaltyCard.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionListDTO {
    List<TransactionDto> listOfTransaction;
}
