package com.statoverflow.status.domain.auth.dto;

import com.statoverflow.status.domain.users.enums.ProviderType;

import lombok.Getter;

public record OAuthLoginRequestDto(
        ProviderType provider,
        String code
) {
}
