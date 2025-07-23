package com.statoverflow.status.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.statoverflow.status.domain.users.enums.ProviderType;

public record OAuthProviderDto(
	@JsonProperty("provider_type")
	ProviderType providerType,

	@JsonProperty("provider_id")
	String providerId) implements SocialLoginReturnDto {

	@Override
	@JsonProperty("type")
	public String type() {
		return "SIGNUP";
	}
}
