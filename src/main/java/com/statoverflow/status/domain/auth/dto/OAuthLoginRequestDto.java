package com.statoverflow.status.domain.auth.dto;

import com.statoverflow.status.domain.users.enums.ProviderType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

public record OAuthLoginRequestDto(
        @Schema(description = "로그인 소셜 플랫폼 (KAKAO, GOOGLE, APPLE 중 하나)")
        ProviderType provider,
        String code
) {
        public static OAuthProviderDto OAuthProviderDto(OAuthLoginRequestDto req) {
                return new OAuthProviderDto(req.provider, req.code);
        }

}
