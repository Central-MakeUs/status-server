package com.statoverflow.status.domain.auth.service;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.TokenResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface OAuthService {
    TokenResponseDto kakaoLogin(OAuthLoginRequestDto request);
}
