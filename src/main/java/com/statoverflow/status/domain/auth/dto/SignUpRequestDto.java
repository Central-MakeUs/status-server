package com.statoverflow.status.domain.auth.dto;

import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.ProviderType;

public record SignUpRequestDto(String nickname, OAuthProviderDto provider) {

	public Users toEntity() {
		String providerId = this.provider.providerId();
		ProviderType providerType = this.provider.providerType();

		return Users.builder()
			.nickname(this.nickname)
			.providerId(providerId)
			.providerType(providerType)
			.uuid("test")
			.build();
	}
}
