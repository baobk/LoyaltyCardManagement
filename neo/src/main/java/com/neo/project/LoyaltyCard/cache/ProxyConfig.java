package com.neo.project.LoyaltyCard.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class ProxyConfig {

    private @Value("#{${spring.redis.mode}") String mode;

    private @Autowired
    Map<String, ICacheStrategy> cacheStrategys = new ConcurrentHashMap<String,ICacheStrategy>();

    private ICacheStrategy cacheStrategy;

    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void init(){
        switch(mode){
            case "single":
                cacheStrategy = new SingleCacheStrategy();
                break;
            case "cluster":
                cacheStrategy = new ClusterCacheStrategy();
                break;
        }

       /* if("single".equals(mode)){
            cacheStrategy = cacheStrategys.get(SingleCacheStrategy.class.getName() + "#0");
        }
        else if ("cluster".equals(mode)){
            cacheStrategy = cacheStrategys.get(ClusterCacheStrategy.class.getName() + "#0");
        }*/
        redisTemplate = cacheStrategy.redisTemplate();
    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        return redisTemplate;
    }

}
