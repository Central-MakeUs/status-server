package com.statoverflow.status.domain.users.controller;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.service.OAuthService;
import com.statoverflow.status.domain.users.dto.NicknameRequestDto;
import com.statoverflow.status.global.annotation.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.auth.service.TokenService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.service.UsersService;
import com.statoverflow.status.global.jwt.JwtService;
import com.statoverflow.status.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "[유저] Users API", description = "사용자 관리 API")
public class UsersController {

	private final UsersService usersService;
	private final TokenService tokenService;
	private final JwtService jwtService;
	private final OAuthService oAuthService;

	@Operation(summary = "회원가입", description = "신규 회원을 등록합니다.")
	@PostMapping("/sign-up")
	public ResponseEntity<ApiResponse<BasicUsersDto>> signUp(@RequestBody SignUpRequestDto req,
		@Parameter(hidden = true) HttpServletResponse response) {
		BasicUsersDto user = usersService.signUp(req);
		tokenService.issueAndSetTokens(user, response);
		return ApiResponse.created(user);
	}


	@Operation(summary = "소셜 아이디 연동", description = "게스트 회원에서 소셜 아이디를 연동합니다.")
	@PatchMapping("/connect-provider")
	public ResponseEntity<ApiResponse<BasicUsersDto>> connectProvider(@CurrentUser BasicUsersDto users,
		@RequestBody OAuthLoginRequestDto req,
		@Parameter(hidden = true) HttpServletResponse response) {

		OAuthProviderDto provider = oAuthService.getProviderId(req);

		BasicUsersDto user = usersService.connectProvider(users, provider);
		tokenService.issueAndSetTokens(user, response);
		return ApiResponse.ok(user);
	}

	@Operation(summary = "닉네임 수정", description = "사용자의 닉네임을 변경합니다. 토큰이 재발급됩니다.")
	@PatchMapping("/nickname")
	public ResponseEntity<ApiResponse<BasicUsersDto>> updateNickname(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto users,
		@RequestBody NicknameRequestDto req,
		@Parameter(hidden = true) HttpServletResponse response) {
		BasicUsersDto updatedUser = usersService.updateNickname(users.id(), req.nickname());
		tokenService.issueAndSetTokens(updatedUser, response);
		return ApiResponse.ok(updatedUser);
	}

	@Operation(summary = "회원 탈퇴", description = "사용자 계정을 삭제하고 로그아웃 처리합니다.")
	@DeleteMapping("/unregister")
	public ResponseEntity<ApiResponse<?>> deleteUser(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto users,
		@Parameter(hidden = true) HttpServletResponse response) {
		// 쿠키 삭제
		// 액세스 토큰 쿠키 삭제 (Max-Age=0)
		jwtService.deleteCookie(response, "access_token", true);
		// 새로고침 토큰 쿠키 삭제 (Max-Age=0)
		jwtService.deleteCookie(response, "refresh_token", true);
		// 새로고침 토큰 쿠키 삭제 (Max-Age=0)
		jwtService.deleteCookie(response, "is_authenticated", false);
		usersService.deleteUser(users.id(), response);
		return ApiResponse.noContent();
	}

	@Operation(summary = "내 정보 조회", description = "현재 로그인한 사용자의 정보를 조회합니다.")
	@GetMapping("/me")
	public ResponseEntity<ApiResponse<BasicUsersDto>> getUser(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto users) {
		return ApiResponse.ok(users);
	}

}