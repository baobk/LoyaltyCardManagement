package com.neo.project.LoyaltyCard.exception;


import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ServiceExceptionAdviceHanlder {

    @ExceptionHandler(ServiceExceptionAdvice.class)
    @ResponseBody
    public ResponseDTO serviceExceptionError(ServiceExceptionAdvice ex) {
        return new ResponseDTO(ex.getCode(),ex.getMessage());
    }
}
