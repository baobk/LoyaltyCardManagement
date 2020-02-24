package com.neo.project.LoyaltyCard.util;

import com.neo.project.LoyaltyCard.dto.core.ResponseDTO;

public class ResponseUtil<T> {

    public static ResponseDTO success(){
        return new ResponseDTO("00000", "Successful");
    }

    public static <T> ResponseDTO<T> success(String code, String msg, T data){
      return new ResponseDTO(code,msg,data);
    };

    public static ResponseDTO error(){
        return new ResponseDTO("11111","Failed");
    }

    public static <T> ResponseDTO<T> error(String code, String msg, T data){
        return new ResponseDTO(code,msg,data);
    }
}
