package com.statoverflow.status.domain.auth.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplePublicKeyResponseDto(
	List<AppleKey> keys
) {
	@JsonIgnoreProperties(ignoreUnknown = true)
	public record AppleKey(
		String kty,
		String kid,
		String use,
		String alg,
		String n,
		String e
	) {
	}
}