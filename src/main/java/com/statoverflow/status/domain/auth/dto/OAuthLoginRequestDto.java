package com.statoverflow.status.domain.auth.dto;

import lombok.Getter;

public record OAuthLoginRequestDto(
        String provider,
        String code
) {
}
