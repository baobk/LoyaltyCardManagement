package com.neo.project.LoyaltyCard.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Slf4j
@Controller
public class aopRoyaltyCardController {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(aopRoyaltyCardController.class);

    @Around("execution(* com.neo.project.LoyaltyCard.controller.*(..))")
    public Object proceedingRoyaltyCardAOP(ProceedingJoinPoint pjp){
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
            HttpServletRequest request = attributes.getRequest();


        } catch (Exception ex){
            return "{\"code\":11111,\"message\":\"Found some error!\"}";
        }
        return null;
    }

}
