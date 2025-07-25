package com.statoverflow.status.domain.users.controller;

import com.statoverflow.status.domain.users.dto.NicknameRequestDto;
import com.statoverflow.status.global.annotation.CurrentUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.auth.service.TokenService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.service.UsersService;
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

	@PostMapping("/sign-up")
	public ResponseEntity<ApiResponse<BasicUsersDto>> signUp(@RequestBody SignUpRequestDto req, HttpServletResponse response) {
		BasicUsersDto user = usersService.signUp(req);
		tokenService.issueAndSetTokens(user, response);
		return ApiResponse.created(user);
	}

	@PatchMapping("/nickname")
	public ResponseEntity<ApiResponse<?>> updateNickname(@CurrentUser BasicUsersDto users, @RequestBody NicknameRequestDto req, HttpServletResponse response) {
		usersService.updateNickname(users.id(), req.nickname());
		return ApiResponse.noContent();
	}

}
