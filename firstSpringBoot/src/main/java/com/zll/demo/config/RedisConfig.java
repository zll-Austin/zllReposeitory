package com.zll.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConfigurationProperties("spring.redis.cluster")
public class RedisConfig {
	List<Integer> ports;
	String host;
	JedisPoolConfig poolConfig;
	@Bean
	RedisClusterConfiguration redisClusterConfiguration() {
		RedisClusterConfiguration configuration = new RedisClusterConfiguration();
		List<RedisNode> nodes = new ArrayList<>();
		for(Integer port: ports) {
			nodes.add(new RedisNode(host, port));
		}
		//configuration.setPassword(RedisPassword.of("!P@ssw0rd"));
		configuration.setClusterNodes(nodes);
		return configuration;
	}
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration(),poolConfig);
		return factory;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	RedisTemplate redisTemplate() {
		RedisTemplate redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		return redisTemplate;
	}
	
	@Bean
	StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
		stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
		stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
		return stringRedisTemplate;
	}

	public List<Integer> getPorts() {
		return ports;
	}

	public void setPorts(List<Integer> ports) {
		this.ports = ports;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public JedisPoolConfig getPoolConfig() {
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	@Override
	public String toString() {
		return "RedisConfig [ports=" + ports + ", host=" + host + ", poolConfig=" + poolConfig + "]";
	}
	
	
}
