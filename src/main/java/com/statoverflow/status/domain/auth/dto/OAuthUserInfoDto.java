package com.statoverflow.status.domain.auth.dto;

public record OAuthUserInfoDto(OAuthProvider provider, String email) {

    public record OAuthProvider(String providerId, ProviderType providerType) {}

    public enum ProviderType {
        KAKAO, GOOGLE, APPLE
    }
}
