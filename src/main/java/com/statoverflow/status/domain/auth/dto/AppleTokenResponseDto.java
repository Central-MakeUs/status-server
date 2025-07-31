package com.statoverflow.status.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AppleTokenResponseDto (

	@JsonProperty("access_token")
	String accessToken,

	@JsonProperty("expires_in")
	Integer expiresIn,

	@JsonProperty("id_token")
	String idToken,

	@JsonProperty("refresh_token")
	String refreshToken,

	@JsonProperty("token_type")
	String tokenType
){
}
