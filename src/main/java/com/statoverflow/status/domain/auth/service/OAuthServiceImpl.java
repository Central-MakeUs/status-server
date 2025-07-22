package com.statoverflow.status.domain.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.auth.dto.KakaoTokenResponseDto;
import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.strategy.AuthCodeProcessor;
import com.statoverflow.status.domain.auth.util.GoogleOAuthClient;
import com.statoverflow.status.domain.auth.util.KakaoOAuthClient;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OAuthServiceImpl implements OAuthService{

    private final GoogleOAuthClient googleClient;
    private final KakaoOAuthClient kakaoClient;

    private final List<AuthCodeProcessor> authCodeProcessors;



    @Override
    public OAuthProviderDto getProviderId(OAuthLoginRequestDto dto) {
        return authCodeProcessors.stream()
            .filter(processor -> processor.supports(dto.provider()))
            .findFirst()
            .map(processor -> processor.getProviderId(dto.code()))
            .orElseThrow(() -> new CustomException(ErrorType.UNSUPPORTED_OAUTH_PROVIDER));
    }


}
