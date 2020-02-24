package com.neo.project.LoyaltyCard.exception;

import lombok.Data;

@Data
public class ServiceExceptionAdvice extends Exception {
    public String code;
    public String message;

    public ServiceExceptionAdvice(){
        super();
    }
    public ServiceExceptionAdvice(String code,String msg){
        this.code=code;
        this.message=msg;
    }


}
