package com.statoverflow.status.domain.quest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.statoverflow.status.domain.quest.dto.AttributeIdDto;
import com.statoverflow.status.domain.quest.dto.ThemeResponseDto;
import com.statoverflow.status.domain.quest.service.QuestService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.global.annotation.CurrentUser;
import com.statoverflow.status.global.response.ApiResponse;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/quest")
@Slf4j
@RequiredArgsConstructor
public class QuestController {

	private final QuestService questService;

	@GetMapping("/get-themes")
	public ResponseEntity<ApiResponse<List<ThemeResponseDto>>> getThemes(@CurrentUser BasicUsersDto user,
		@RequestParam List<Integer> attributes) {

		return ApiResponse.ok(questService.getThemes(attributes));

	}

}
