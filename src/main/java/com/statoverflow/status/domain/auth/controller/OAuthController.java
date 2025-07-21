package com.statoverflow.status.domain.auth.controller;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.KakaoTokenResponseDto;
import com.statoverflow.status.domain.auth.service.OAuthService;
import com.statoverflow.status.domain.auth.service.TokenService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.enums.ProviderType;
import com.statoverflow.status.domain.users.service.UsersService;
import com.statoverflow.status.global.annotation.CurrentUser;
import com.statoverflow.status.global.response.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
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
    private final UsersService usersService;
    private final TokenService tokenService;

    @PostMapping("/kakao-login")
    public ResponseEntity<ApiResponse<BasicUsersDto>> oauthLogin(@RequestBody OAuthLoginRequestDto request,
                                                                   HttpServletResponse response) {
        log.info("카카오 로그인 요청 수신, 코드: {}", request.code());

        // 카카오 토큰 발급 후 식별자 코드 발급
        String providerId = oAuthService.getProviderId(request.code());

        // 식별자 코드로 user 정보 받기(없다면 회원가입 처리)
        BasicUsersDto user = usersService.getUsersByProvider(ProviderType.KAKAO.name(), providerId);

        // todo: AccessToken, RefreshToken 을 발급 후 HttpOnly 쿠키에 저장
        tokenService.issueAndSetTokens(user, response);

        return ApiResponse.ok(user);

    }

}