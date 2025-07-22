package com.statoverflow.status.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public record GoogleUserInfoDto(

	String sub,

	String email,

	@JsonProperty("verified_email")
	Boolean verifiedEmail,

	String name,

	@JsonProperty("given_name")
	String givenName,

	@JsonProperty("family_name")
	String familyName,

	String picture,

	String locale
) {
}
