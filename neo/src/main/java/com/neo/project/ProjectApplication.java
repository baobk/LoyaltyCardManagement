package com.neo.project;

import com.neo.project.LoyaltyCard.mapper.PointConfigMapper;
import com.neo.project.LoyaltyCard.pojo.LoyaltyCard;
import com.neo.project.LoyaltyCard.service.LoyaltyCardService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

import java.util.List;


@SpringBootApplication(scanBasePackages = "com.neo.project")
@Configuration
@MapperScan("com.neo.project.LoyaltyCard.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = false)
@PropertySource("classpath:application.properties")
public class ProjectApplication implements CommandLineRunner {

    @Autowired
    LoyaltyCardService loyaltyCardService;

    @Override
    public void run(String... args) throws Exception {
       List<LoyaltyCard> list = loyaltyCardService.selectAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);

    }

}
