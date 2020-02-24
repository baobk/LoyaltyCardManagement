package com.neo.project.LoyaltyCard.service.impl;

import com.neo.project.LoyaltyCard.dto.PointConfigDto;
import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;
import com.neo.project.LoyaltyCard.mapper.PointConfigMapper;
import com.neo.project.LoyaltyCard.service.PointConfigService;
import com.neo.project.LoyaltyCard.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointConfigServiceImpl implements PointConfigService {

    @Autowired
    PointConfigMapper pointConfigMapper;

    @Override
    public ResponseDTO update(RequestDTO<PointConfigDto> requestDto) {
        if(pointConfigMapper.checkConfig() > 0) {
            pointConfigMapper.update(requestDto.getBody().getPoint());
        } else {
            pointConfigMapper.insert(requestDto.getBody().getPoint());
        }
        return  ResponseUtil.success();
    }
}
