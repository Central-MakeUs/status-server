package com.statoverflow.status.domain.auth.strategy;

import org.springframework.stereotype.Component;

import com.statoverflow.status.domain.auth.dto.AppleTokenResponseDto;
import com.statoverflow.status.domain.auth.dto.GoogleTokenResponseDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.jwt.AppleIdTokenValidator;
import com.statoverflow.status.domain.auth.util.AppleOAuthClient;
import com.statoverflow.status.domain.auth.util.GoogleOAuthClient;
import com.statoverflow.status.domain.users.enums.ProviderType;
import com.statoverflow.status.global.jwt.JwtService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component // 스프링 빈으로 등록
@RequiredArgsConstructor
public class AppleAuthCodeProcessor implements AuthCodeProcessor {

	private final AppleOAuthClient appleOAuthClient;
	private final AppleIdTokenValidator appleIdTokenValidator;

	@Override
	public boolean supports(ProviderType provider) {
		return ProviderType.APPLE == provider;
	}

	@Override
	public OAuthProviderDto getProviderId(String code) {

		// 인가 토큰 얻기
		AppleTokenResponseDto tokenResponse = appleOAuthClient.getAppleTokens(code);

		// idToken 파싱
		Claims claims = appleIdTokenValidator.validateAppleIdTokenAndGetClaims(tokenResponse.idToken());

		// Apple에서 제공하는 사용자 정보 추출
		String subject = claims.getSubject();

		return new OAuthProviderDto(ProviderType.APPLE, subject);
	}
}