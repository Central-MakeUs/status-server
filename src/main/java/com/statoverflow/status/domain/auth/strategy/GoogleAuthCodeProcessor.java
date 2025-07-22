package com.statoverflow.status.domain.auth.strategy;

import com.statoverflow.status.domain.auth.dto.GoogleTokenResponseDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.util.GoogleOAuthClient; // Google API 호출 클라이언트 (구현 필요)
import com.statoverflow.status.domain.users.enums.ProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component; // 또는 Service

@Component // 스프링 빈으로 등록
@RequiredArgsConstructor
public class GoogleAuthCodeProcessor implements AuthCodeProcessor {

	private final GoogleOAuthClient googleOAuthClient; // Google API와 통신할 클라이언트 주입 (구현 필요)

	@Override
	public boolean supports(ProviderType provider) {
		return ProviderType.GOOGLE == provider;
	}

	@Override
	public OAuthProviderDto getProviderId(String code) {
		// TODO: 실제 구글 API 호출 로직 구현 (인가 코드로 액세스 토큰 및 ID 토큰 얻기)
		// 예시:
		GoogleTokenResponseDto tokenResponse = googleOAuthClient.getGoogleTokens(code);

		// 유저 정보 조회
		String providerId = googleOAuthClient.getUserInfo(tokenResponse);

		return new OAuthProviderDto(ProviderType.GOOGLE, providerId);
	}
}