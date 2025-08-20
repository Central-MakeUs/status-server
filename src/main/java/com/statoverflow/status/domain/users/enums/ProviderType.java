package com.statoverflow.status.domain.users.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderType {
	KAKAO(LoginType.SOCIAL), GOOGLE(LoginType.SOCIAL), APPLE(LoginType.SOCIAL), GUEST(LoginType.GUEST);

	private final LoginType field;

	public enum LoginType {
		SOCIAL, GUEST
	}
}
