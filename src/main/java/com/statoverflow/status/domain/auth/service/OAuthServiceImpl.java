package com.statoverflow.status.domain.auth.service;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.auth.dto.OAuthUserInfoDto;
import com.statoverflow.status.domain.auth.dto.KakaoTokenResponseDto;
import com.statoverflow.status.domain.auth.util.GoogleOAuthClient;
import com.statoverflow.status.domain.auth.util.KakaoOAuthClient;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OAuthServiceImpl implements OAuthService{

    private final GoogleOAuthClient googleClient;
    private final KakaoOAuthClient kakaoClient;



    @Override
    public String getProviderId(String code) {

        // 소셜 토큰 발급
        KakaoTokenResponseDto tokens = getKakaoToken(code);

        // 유저 정보 조회
        Long providerId = kakaoClient.getUserInfo(tokens.getAccessToken());

        return Long.toString(providerId);
    }

    public KakaoTokenResponseDto getKakaoToken(String code) {
        return kakaoClient.getKakaoTokens(code);
    }
}
