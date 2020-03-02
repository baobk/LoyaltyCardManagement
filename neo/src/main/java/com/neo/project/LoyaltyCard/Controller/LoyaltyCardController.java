package com.neo.project.LoyaltyCard.controller;


import com.neo.project.LoyaltyCard.dto.PointConfigDto;
import com.neo.project.LoyaltyCard.dto.TransactionListDTO;
import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;
import com.neo.project.LoyaltyCard.service.PointConfigService;
import com.neo.project.LoyaltyCard.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@Slf4j
@ResponseBody
@RequestMapping(value = "LoyaltyCard/")
public class LoyaltyCardController {

    @Autowired
    PointConfigService pointConfigService;

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value="updatePointConfig",method = RequestMethod.PUT)
    public ResponseDTO login(@Valid @RequestBody RequestDTO<PointConfigDto> requestDto){
        return  pointConfigService.update(requestDto);
    }

    @RequestMapping(value="addTransactions",method = RequestMethod.POST)
    public ResponseDTO updateLoyaltyCard(@Valid @RequestBody RequestDTO<TransactionListDTO> requestDto) throws Exception {
        return  transactionService.handleTransactions(requestDto);
    }
}
