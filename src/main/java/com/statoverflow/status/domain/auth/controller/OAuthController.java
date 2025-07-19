package com.statoverflow.status.domain.auth.controller;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.TokenResponseDto;
import com.statoverflow.status.domain.auth.service.OAuthService;
import com.statoverflow.status.global.response.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @PostMapping("/kakao-login")
    public ResponseEntity<ApiResponse<TokenResponseDto>> oauthLogin(@RequestBody OAuthLoginRequestDto request,
                                                                   HttpServletResponse response) {
        log.info("카카오 로그인 요청 수신, 코드: {}", request.accessCode());
        // 소셜 API 로 유저 정보 인증
        TokenResponseDto tokens = oAuthService.login(request);

        // todo: AccessToken 을 Redis에 저장


        // todo: RefreshToken을 HttpOnly 쿠키에 저장 - 메서드 분리
        Cookie refreshCookie = new Cookie("refreshToken", tokens.refreshToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(refreshCookie);

        return ApiResponse.ok(tokens);

    }
}