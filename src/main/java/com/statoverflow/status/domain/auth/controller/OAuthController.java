package com.statoverflow.status.domain.auth.controller;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.auth.service.OAuthService;
import com.statoverflow.status.domain.auth.service.TokenService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.enums.ProviderType;
import com.statoverflow.status.domain.users.service.UsersService;
import com.statoverflow.status.global.response.ApiResponse;

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
    private final UsersService usersService;
    private final TokenService tokenService;

    @PostMapping("/kakao-login")
    public ResponseEntity<ApiResponse<SocialLoginReturnDto>> kakaoOauthLogin(@RequestBody OAuthLoginRequestDto request,
        HttpServletResponse response) {
        log.info("카카오 로그인 요청 수신, 코드: {}", request.code());

        OAuthLoginRequestDto req = new OAuthLoginRequestDto(ProviderType.KAKAO, request.code());

        // 카카오 토큰 발급 후 식별자 코드 발급
        OAuthProviderDto provider = oAuthService.getProviderId(req);

        // 식별자 코드로 user 정보 받기
        SocialLoginReturnDto res = usersService.getUsersByProvider(provider);

        if(res instanceof BasicUsersDto) {
            // AccessToken, RefreshToken 을 발급 후 HttpOnly 쿠키에 저장
            tokenService.issueAndSetTokens((BasicUsersDto) res, response);

        }

        return ApiResponse.ok(res);

    }

    @PostMapping("/google-login")
    public ResponseEntity<ApiResponse<SocialLoginReturnDto>> googleOauthLogin(@RequestBody OAuthLoginRequestDto request,
        HttpServletResponse response) {
        log.info("구글 로그인 요청 수신, 코드: {}", request.code());

        OAuthLoginRequestDto req = new OAuthLoginRequestDto(ProviderType.GOOGLE, request.code());

        // 구글 토큰 발급 후 식별자 코드 발급
        OAuthProviderDto provider = oAuthService.getProviderId(req);

        // 식별자 코드로 user 정보 받기(없다면 회원가입 처리)
        SocialLoginReturnDto res = usersService.getUsersByProvider(provider);

        if(res instanceof BasicUsersDto) {
            // AccessToken, RefreshToken 을 발급 후 HttpOnly 쿠키에 저장
            tokenService.issueAndSetTokens((BasicUsersDto) res, response);

        }

        return ApiResponse.ok(res);

    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<BasicUsersDto>> signUp(@RequestBody SignUpRequestDto req, HttpServletResponse response) {
        BasicUsersDto user = usersService.signUp(req);
        tokenService.issueAndSetTokens(user, response);
        return ApiResponse.ok(user);
    }

}