package com.statoverflow.status.domain.auth.dto;

public record OAuthLoginRequestDto(
        String provider,
        String accessToken
) {
}
