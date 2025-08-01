package com.statoverflow.status.domain.users.controller;

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

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

	private final UsersService usersService;
	private final TokenService tokenService;
	private final JwtService jwtService;

	@PostMapping("/sign-up")
	public ResponseEntity<ApiResponse<BasicUsersDto>> signUp(@RequestBody SignUpRequestDto req, HttpServletResponse response) {
		BasicUsersDto user = usersService.signUp(req);
		tokenService.issueAndSetTokens(user, response);
		return ApiResponse.created(user);
	}

	@PatchMapping("/nickname")
	public ResponseEntity<ApiResponse<BasicUsersDto>> updateNickname(@CurrentUser BasicUsersDto users, @RequestBody NicknameRequestDto req, HttpServletResponse response) {
		BasicUsersDto updatedUser = usersService.updateNickname(users.id(), req.nickname());
		tokenService.issueAndSetTokens(updatedUser, response);
		return ApiResponse.ok(updatedUser);
	}

	@DeleteMapping("/unregister")
	public ResponseEntity<ApiResponse<?>> deleteUser(@CurrentUser BasicUsersDto users, HttpServletResponse response) {
		// 쿠키 삭제
		jwtService.deleteCookie(response, "access_token");
		jwtService.deleteCookie(response, "refresh_token");
		usersService.deleteUser(users.id(), response);
		return ApiResponse.noContent();
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<BasicUsersDto>> getUser(@CurrentUser BasicUsersDto users) {
		return ApiResponse.ok(users);
	}

}
