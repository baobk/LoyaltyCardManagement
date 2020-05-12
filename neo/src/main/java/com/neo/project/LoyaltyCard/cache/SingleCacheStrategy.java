package com.neo.project.LoyaltyCard.cache;

import com.neo.project.CacheConfig.CacheStrategy;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component("singleCacheStrategy")
public class SingleCacheStrategy implements ICacheStrategy {

        @Value("${spring.redis.host}")
        private String redisHost;

        @Value("${spring.redis.port}")
        private int redisPort;

        @Value("${spring.redis.password}")
        private String redisPwd;

        @Value("${spring.redis.timeout}")
        private long timeout;

        @Value("${spring.redis.lettuce.pool.max-idle}")
        private int maxIdle;

        @Value("${spring.redis.lettuce.pool.min-idle}")
        private int minIdle;

        @Value("${spring.redis.lettuce.pool.max-active}")
        private int maxActive;

        @Value("${spring.redis.lettuce.pool.max-wait}")
        private long maxWait;

        @Bean
        public RedisConnectionFactory redisConnectionFactory(GenericObjectPoolConfig genericObjectPoolConfig) {
                RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
                redisStandaloneConfiguration.setPassword(redisPwd);
                redisStandaloneConfiguration.setHostName(redisHost);
                redisStandaloneConfiguration.setPort(redisPort);

                LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                        .commandTimeout(Duration.ofMillis(timeout))
                        .poolConfig(genericObjectPoolConfig)
                        .build();

                LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration,clientConfig);
                return lettuceConnectionFactory;
        }

        @Bean
        public GenericObjectPoolConfig genericObjectPoolConfig() {
                GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
                genericObjectPoolConfig.setMaxIdle(maxIdle);
                genericObjectPoolConfig.setMinIdle(minIdle);
                genericObjectPoolConfig.setMaxTotal(maxActive);
                genericObjectPoolConfig.setMaxWaitMillis(maxWait);
                return genericObjectPoolConfig;
        }

        @Override
        public RedisTemplate<String, Object> redisTemplate() {
                RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
                redisTemplate.setConnectionFactory(redisConnectionFactory(genericObjectPoolConfig()));
                return redisTemplate;
        }
}
