package com.statoverflow.status.domain.auth.strategy;

import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.util.KakaoOAuthClient;
import com.statoverflow.status.domain.auth.dto.KakaoTokenResponseDto;
import com.statoverflow.status.domain.users.enums.ProviderType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeProcessor implements AuthCodeProcessor {

	private final KakaoOAuthClient kakaoOAuthClient;

	@Override
	public boolean supports(ProviderType provider) {
		return ProviderType.KAKAO == provider;
	}

	@Override
	public OAuthProviderDto getProviderId(String code) {
		// 1. 카카오 토큰 발급
		KakaoTokenResponseDto tokens = kakaoOAuthClient.getKakaoTokens(code);

		// 2. 유저 정보 조회 (식별자 코드)
		Long providerId = kakaoOAuthClient.getUserInfo(tokens.getAccessToken());

		return new OAuthProviderDto(ProviderType.KAKAO, Long.toString(providerId));
	}
}