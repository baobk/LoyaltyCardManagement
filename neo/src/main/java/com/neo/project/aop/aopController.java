package com.neo.project.aop;

import com.neo.project.Configuration.RedisCache;
import com.neo.project.Dto.GroupDto;
import com.neo.project.Dto.UserDto;
import com.neo.project.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Aspect
@Component
@Slf4j
public class aopController {

    private String systemId;

    @Around("execution(* com.neo.project.Controller.*.*(com.neo.project.Dto.GroupDto))")
    public GroupDto testGroup(ProceedingJoinPoint pjp)throws Throwable {

        MethodSignature joinMethodSignature = (MethodSignature) pjp.getSignature();
        RequestMapping classAnnotation = AnnotationUtils.findAnnotation(joinMethodSignature.getMethod(),RequestMapping.class);
        RequestMapping methodAnnotation = AnnotationUtils.findAnnotation(joinMethodSignature.getMethod().getDeclaringClass(),RequestMapping.class);

        String[] rootUrl = classAnnotation.value();
        String[] methodUrl = methodAnnotation.value();

        GroupDto dto = (GroupDto) pjp.getArgs()[0];
        dto.setName("group aop");
        return (GroupDto) pjp.proceed();
    }


    @Around("execution(* com.neo.project.Controller.*.*(com.neo.project.Dto.UserDto))")
    public UserDto testLogin(ProceedingJoinPoint pjp)throws Throwable {

        RedisCache cache = new RedisCache();


        UserDto dto = (UserDto) pjp.getArgs()[0];
        dto.setName("user aop");
        dto.setSystem(systemId);
        return (UserDto)pjp.proceed();
    }



}
