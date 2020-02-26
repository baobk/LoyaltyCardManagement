package com.neo.project.LoyaltyCard.controller;


import com.neo.project.LoyaltyCard.dto.PointConfigDto;
import com.neo.project.LoyaltyCard.dto.TransactionDto;
import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;
import com.neo.project.LoyaltyCard.service.LoyaltyCardService;
import com.neo.project.LoyaltyCard.service.PointConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@ResponseBody
@RequestMapping(value = "LoyaltyCard/")
public class LoyaltyCardController {

    @Autowired
    PointConfigService pointConfigService;

    @Autowired
    LoyaltyCardService loyaltyCardService;

    @RequestMapping(value="updatePointConfig",method = RequestMethod.PUT)
    public ResponseDTO login(@Valid @RequestBody RequestDTO<PointConfigDto> requestDto){
        return  pointConfigService.update(requestDto);
    }

    @RequestMapping(value="updateLoyaltyCard",method = RequestMethod.POST)
    public ResponseDTO updateLoyaltyCard(@Valid @RequestBody RequestDTO<List<TransactionDto>> requestDto) throws Exception {
        return  loyaltyCardService.updateLoyaltyCard(requestDto);
    }
}
