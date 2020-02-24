package com.neo.project.Dto;

import com.neo.project.LoyaltyCard.dto.core.HeadDTO;
import lombok.Data;

@Data
public class RequestDto<T> extends HeadDTO {
    private T innerData;
}
