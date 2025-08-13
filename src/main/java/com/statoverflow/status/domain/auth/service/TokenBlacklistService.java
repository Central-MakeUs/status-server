package com.statoverflow.status.domain.auth.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.RedisTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenBlacklistService {

	private final RedisTemplate<String, String> redisTemplate;

	// 로그아웃 시 토큰을 블랙리스트에 추가
	public void addToBlacklist(String token, Long expiration) {
		// 토큰을 key로, 'logout' 등의 값을 value로 저장
		// expireInSeconds는 토큰의 남은 유효 시간
		redisTemplate.opsForValue().set(token, "logout", expiration, TimeUnit.SECONDS);
	}

	// 토큰이 블랙리스트에 있는지 확인
	public boolean isBlacklisted(String accessToken) {
		// Redis에 토큰이 존재하면 true 반환
		return redisTemplate.hasKey(accessToken);
	}
}