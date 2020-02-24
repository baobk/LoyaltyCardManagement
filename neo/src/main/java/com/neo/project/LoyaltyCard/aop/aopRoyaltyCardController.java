package com.neo.project.LoyaltyCard.aop;

import com.neo.project.LoyaltyCard.dto.core.RequestDTO;
import com.neo.project.LoyaltyCard.exception.ServiceExceptionAdvice;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

@Slf4j
public class aopRoyaltyCardController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(aopRoyaltyCardController.class);

    @Around("execution(* com.neo.project.LoyaltyCard.Controller.*.*(com.neo.project.LoyaltyCard.dto.core.RequestDTO))")
    public RequestDTO proceedingRoyaltyCardAOP(ProceedingJoinPoint pjp)throws Throwable {
        RequestDTO dto = (RequestDTO) pjp.getArgs()[0];
        try {
            dto = (RequestDTO) pjp.proceed();
        } catch (ServiceExceptionAdvice ex){
            ex.printStackTrace();
            throw ex;
        }
        return dto;
    }

}
