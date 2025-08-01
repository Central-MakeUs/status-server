package com.statoverflow.status.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.auth.service.OAuthService;
import com.statoverflow.status.domain.auth.service.TokenService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.enums.ProviderType;
import com.statoverflow.status.domain.users.service.UsersService;
import com.statoverflow.status.global.annotation.CurrentUser;
import com.statoverflow.status.global.jwt.JwtService;
import com.statoverflow.status.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "[인증] Auth API", description = "소셜 로그인 및 토큰 관리 API")
public class OAuthController {

    private final OAuthService oAuthService;
    private final UsersService usersService;
    private final TokenService tokenService;
    private final JwtService jwtService;

    @Operation(summary = "1. 카카오 소셜 로그인",
        description = "카카오로부터 인가 코드를 받아 로그인 및 회원가입을 진행합니다.")
    @PostMapping("/kakao-login")
    public ResponseEntity<ApiResponse<SocialLoginReturnDto>> kakaoOauthLogin(
        @RequestBody OAuthLoginRequestDto request,
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

    @Operation(summary = "2. 구글 소셜 로그인",
        description = "구글로부터 인가 코드를 받아 로그인 및 회원가입을 진행합니다.")
    @PostMapping("/google-login")
    public ResponseEntity<ApiResponse<SocialLoginReturnDto>> googleOauthLogin(
        @RequestBody OAuthLoginRequestDto request,
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

    @Operation(summary = "3. 애플 소셜 로그인",
        description = "애플로부터 인가 코드를 받아 로그인 및 회원가입을 진행합니다.")
    @PostMapping("/apple-login")
    public ResponseEntity<ApiResponse<SocialLoginReturnDto>> appleOauthLogin(@RequestParam String code,
        @RequestParam(required = false) String user, HttpServletResponse response) {
        log.info("애플 로그인 요청 수신, 코드: {}", code);
        log.info("애플 로그인 요청 수신, 유저 정보: {}", user);

        OAuthLoginRequestDto req = new OAuthLoginRequestDto(ProviderType.APPLE, code);

        // 애플 토큰 발급 후 식별자 코드 발급
        OAuthProviderDto provider = oAuthService.getProviderId(req);

        // 식별자 코드로 user 정보 받기(없다면 회원가입 처리)
        SocialLoginReturnDto res = usersService.getUsersByProvider(provider);

        if(res instanceof BasicUsersDto) {
            // AccessToken, RefreshToken 을 발급 후 HttpOnly 쿠키에 저장
            tokenService.issueAndSetTokens((BasicUsersDto) res, response);

        }

        return ApiResponse.ok(res);
    }

	@Operation(summary = "4. 액세스 토큰 재발급",
		description = "리프레시 토큰을 이용하여 새로운 액세스 토큰을 발급합니다.")
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<BasicUsersDto>> getAccessToken(
        @Parameter(hidden = true) HttpServletRequest request,
        @Parameter(hidden = true) HttpServletResponse response){
        log.info("엑세스 토큰 재발급 요청 수신");
        String refreshTokenOpt = jwtService.resolveTokenFromCookie(request, "refresh_token");
        jwtService.validateToken(refreshTokenOpt);
        BasicUsersDto res = jwtService.parseUsersFromToken(refreshTokenOpt);
        tokenService.issueAndSetTokens(res, response);

        return ApiResponse.ok(res);
    }

    @Operation(summary = "5. 로그아웃",
        description = "사용자 로그아웃을 처리하고 쿠키에 저장된 토큰을 삭제합니다.")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(
        @Parameter(hidden = true) @CurrentUser BasicUsersDto user,
        @Parameter(hidden = true) HttpServletResponse response){
        log.info("로그아웃 요청 수신, 유저: {}", user.toString());

        // 액세스 토큰 쿠키 삭제 (Max-Age=0)
        jwtService.deleteCookie(response, "access_token");
        // 새로고침 토큰 쿠키 삭제 (Max-Age=0)
        jwtService.deleteCookie(response, "refresh_token");

        // todo: user.provider 에 따라 소셜 로그아웃 필요

        log.info("사용자 로그아웃 처리 및 쿠키 삭제 지시 완료.");

        return ApiResponse.noContent();
    }
}