package com.neo.project.LoyaltyCard.service;

import com.neo.project.LoyaltyCard.dto.PointConfigDto;
import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;

public interface PointConfigService {
    ResponseDTO update(RequestDTO<PointConfigDto> requestDto);
}
