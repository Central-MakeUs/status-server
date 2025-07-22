package com.statoverflow.status.domain.auth.dto;

import com.statoverflow.status.domain.users.enums.ProviderType;

public record OAuthProviderDto(ProviderType providerType, String providerId) {}
