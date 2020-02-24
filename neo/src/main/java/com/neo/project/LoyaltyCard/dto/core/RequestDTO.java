package com.neo.project.LoyaltyCard.dto.core;

import lombok.Data;

@Data
public class RequestDTO<T> extends HeadDTO {
    T body;
}
