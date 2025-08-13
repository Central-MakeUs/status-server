package com.statoverflow.status.domain.auth.service;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface OAuthService {

    OAuthProviderDto getProviderId(OAuthLoginRequestDto dto);

	BasicUsersDto getAccessToken(HttpServletRequest request, HttpServletResponse response);

	void logout(HttpServletRequest request, HttpServletResponse response);
}
