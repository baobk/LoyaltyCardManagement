package com.neo.project.LoyaltyCard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.neo.project.LoyaltyCard.dto.core.HeadDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TransactionDto extends HeadDTO {
    int loyaltyCardId;
    double pointAdjust;
    double spentAdjust;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    Date createDate;
}
