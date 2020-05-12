package com.neo.project.LoyaltyCard.cache;

import org.springframework.data.redis.core.RedisTemplate;

public interface ICacheStrategy {
    public RedisTemplate<String,Object> redisTemplate();
}
