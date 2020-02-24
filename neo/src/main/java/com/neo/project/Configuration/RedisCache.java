package com.neo.project.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RedisCache {

    Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);


    private String redisHost;

    @Autowired
    Map<String, CacheStrategy> cacheStrategys = new ConcurrentHashMap<String,CacheStrategy>();

    @PostConstruct
    void init(){

       // CacheStrategy a = cacheStrategys.get("single");

       //CacheStrategy a= cacheStrategys.get(ClusterCacheStrategy.class.getName() + "#O");
       CacheStrategy b= cacheStrategys.get("singleCache");

    }
}
