package com.zll.demo.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class RedisCacheConfig {
	
	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	RedisCacheManager redisCacheManager() {
		Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
		RedisCacheConfiguration redisCacheConfig = RedisCacheConfiguration.defaultCacheConfig().prefixKeysWith("").disableCachingNullValues().entryTtl(Duration.ofMinutes(30));
		configMap.put("c1", redisCacheConfig);
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisCacheWriter, RedisCacheConfiguration.defaultCacheConfig(), configMap);
		return redisCacheManager;
	}
	
	
}
