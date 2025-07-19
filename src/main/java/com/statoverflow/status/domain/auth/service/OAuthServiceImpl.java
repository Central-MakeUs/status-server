package com.statoverflow.status.domain.auth.service;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthUserInfoDto;
import com.statoverflow.status.domain.auth.dto.TokenResponseDto;
import com.statoverflow.status.domain.auth.util.GoogleOAuthClient;
import com.statoverflow.status.domain.auth.util.KakaoOAuthClient;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class OAuthServiceImpl implements OAuthService{

    private final GoogleOAuthClient googleClient;
    private final KakaoOAuthClient kakaoClient;

    @Override
    public TokenResponseDto kakaoLogin(OAuthLoginRequestDto request) {

        // 소셜 토큰 검증 및 유저 정보 조회
        OAuthUserInfoDto tokenUserInfo = kakaoClient.getUserId(request.accessCode());

        // todo: DB 내 유저 정보 가져오기

        // todo: JWT 토큰 발급

        return new TokenResponseDto("", "");
    }
}
