package com.statoverflow.status.domain.auth.service;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.global.jwt.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

	private final JwtService jwtService;

	public void issueAndSetTokens(BasicUsersDto user, HttpServletResponse response) {
		String accessToken = jwtService.generateAccessToken(user);
		String refreshToken = jwtService.generateRefreshToken(user);

		ResponseCookie accessTokenCookie = setTokenCookie("access_token", accessToken,
			jwtService.getAccessTokenValidityInSeconds());

		ResponseCookie refreshTokenCookie = setTokenCookie("refresh_token", refreshToken,
			jwtService.getRefreshTokenValidityInSeconds());

		response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());
		response.addHeader(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString());
	}


	static ResponseCookie setTokenCookie(String name, String token, Long maxAge) {
		return ResponseCookie.from(name, token)
			.httpOnly(true)
			.secure(true)
			.path("/")
			.sameSite("Lax")
			.maxAge(Duration.ofSeconds(maxAge))
			.build();
	}
}

