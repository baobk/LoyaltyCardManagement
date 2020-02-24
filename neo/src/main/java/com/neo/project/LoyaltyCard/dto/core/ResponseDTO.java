package com.neo.project.LoyaltyCard.dto.core;

import lombok.Data;

@Data
public class ResponseDTO<T>{
    String code;
    String message;
    T data;

    public ResponseDTO(){
    }

    public ResponseDTO(String code, String msg){
        this.code = code;
        this.message = msg;
    }

    public ResponseDTO(String code, String msg, T data){
        this.code = code;
        this.message = msg;
        this.data = data;
    }

}
