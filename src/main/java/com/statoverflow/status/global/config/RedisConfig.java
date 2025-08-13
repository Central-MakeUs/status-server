package com.statoverflow.status.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(connectionFactory);

		// Key는 String으로 직렬화하여 사용
		redisTemplate.setKeySerializer(new StringRedisSerializer());

		// Value도 String으로 직렬화하여 사용 (JSON 등 다른 포맷도 가능)
		redisTemplate.setValueSerializer(new StringRedisSerializer());

		return redisTemplate;
	}
}