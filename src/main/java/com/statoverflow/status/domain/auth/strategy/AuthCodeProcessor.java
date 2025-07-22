package com.statoverflow.status.domain.auth.strategy;

import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.users.enums.ProviderType;

public interface AuthCodeProcessor {
	/**
	 * 이 프로세서가 특정 provider를 처리할 수 있는지 여부를 반환합니다.
	 * @param provider OAuth 제공자 타입 (예: "kakao", "google")
	 * @return 이 프로세서가 provider를 지원하면 true, 아니면 false
	 */
	boolean supports(ProviderType provider);

	/**
	 * 인가 코드를 사용하여 providerId를 가져옵니다.
	 * @param code OAuth 인가 코드
	 * @return 해당 사용자의 고유 providerId (String)
	 */
	OAuthProviderDto getProviderId(String code);
}