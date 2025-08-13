package com.statoverflow.status.domain.auth.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.auth.dto.KakaoTokenResponseDto;
import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.strategy.AuthCodeProcessor;
import com.statoverflow.status.domain.auth.util.GoogleOAuthClient;
import com.statoverflow.status.domain.auth.util.KakaoOAuthClient;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;
import com.statoverflow.status.global.jwt.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class OAuthServiceImpl implements OAuthService{

    private final List<AuthCodeProcessor> authCodeProcessors;
    private final TokenService tokenService;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    @Override
    public OAuthProviderDto getProviderId(OAuthLoginRequestDto dto) {
        return authCodeProcessors.stream()
            .filter(processor -> processor.supports(dto.provider()))
            .findFirst()
            .map(processor -> processor.getProviderId(dto.code()))
            .orElseThrow(() -> new CustomException(ErrorType.UNSUPPORTED_OAUTH_PROVIDER));
    }

    @Override
    public BasicUsersDto getAccessToken(HttpServletRequest request, HttpServletResponse response) {
        log.info("엑세스 토큰 재발급 요청 수신");
        String refreshTokenOpt = jwtService.resolveTokenFromCookie(request, "refresh_token");
        jwtService.validateToken(refreshTokenOpt);
        BasicUsersDto res = jwtService.parseUsersFromToken(refreshTokenOpt);
        tokenService.issueAndSetTokens(res, response);

        return res;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = jwtService.resolveTokenFromCookie(request, "access_token");
        String refreshToken = jwtService.resolveTokenFromCookie(request, "refresh_token");

        tokenBlacklistService.addToBlacklist(accessToken, jwtService.getRemainingTime(accessToken));
        tokenBlacklistService.addToBlacklist(refreshToken, jwtService.getRemainingTime(refreshToken));

        // 액세스 토큰 쿠키 삭제 (Max-Age=0)
        jwtService.deleteCookie(response, "access_token", true);
        // 새로고침 토큰 쿠키 삭제 (Max-Age=0)
        jwtService.deleteCookie(response, "refresh_token", true);
        // 새로고침 토큰 쿠키 삭제 (Max-Age=0)
        jwtService.deleteCookie(response, "is_authenticated", false);

    }

}
