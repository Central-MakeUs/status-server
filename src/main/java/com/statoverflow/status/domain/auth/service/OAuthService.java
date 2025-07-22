package com.statoverflow.status.domain.auth.service;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;

public interface OAuthService {

    OAuthProviderDto getProviderId(OAuthLoginRequestDto dto);
}
