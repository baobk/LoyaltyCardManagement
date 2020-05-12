package com.neo.project.LoyaltyCard.util;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;

public class JsonUtil<T> {

    public static <T> T convertJsonToBean(final String json,TypeReference<T> T){
        return (T) JSONObject.parse(json);
    }

    public static <T> String convertBeanToJson(T data){
        return JSONObject.toJSONString(data);
    }

}
