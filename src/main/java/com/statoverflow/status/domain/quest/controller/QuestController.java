package com.statoverflow.status.domain.quest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.statoverflow.status.domain.quest.dto.MainQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.RerollSubQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.ThemeResponseDto;
import com.statoverflow.status.domain.quest.service.MainQuestService;
import com.statoverflow.status.domain.quest.service.SubQuestService;
import com.statoverflow.status.domain.quest.service.ThemeService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.global.annotation.CurrentUser;
import com.statoverflow.status.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/quest")
@Slf4j
@RequiredArgsConstructor
public class QuestController {

	private final ThemeService themeService;
	private final MainQuestService mainQuestService;
	private final SubQuestService subQuestService;

	@GetMapping("/get-themes")
	public ResponseEntity<ApiResponse<List<ThemeResponseDto>>> getThemes(@CurrentUser BasicUsersDto user,
		@RequestParam List<Integer> attributes) {

		return ApiResponse.ok(themeService.getThemes(attributes));

	}

	@GetMapping("/reroll-themes")
	public ResponseEntity<ApiResponse<List<ThemeResponseDto>>> rerollThemes(@CurrentUser BasicUsersDto user,
		@RequestParam List<Integer> attributes,
		@RequestParam List<Integer> themes) {

		return ApiResponse.ok(themeService.rerollThemes(attributes, themes));

	}

	@GetMapping("/get-mainquests")
	public ResponseEntity<ApiResponse<List<MainQuestResponseDto>>> getMainQuests(@CurrentUser BasicUsersDto user,
		@RequestParam List<Integer> attributes,
		@RequestParam Long theme) {

		return ApiResponse.ok(mainQuestService.getMainQuests(attributes, user.id(), theme));

	}

	@GetMapping("/reroll-mainquests")
	public ResponseEntity<ApiResponse<List<MainQuestResponseDto>>> rerollMainQuests(@CurrentUser BasicUsersDto user,
		@RequestParam List<Integer> attributes,
		@RequestParam Long theme,
		@RequestParam List<Long> mainQuests) {

		return ApiResponse.ok(mainQuestService.rerollMainQuests(attributes, mainQuests, user.id(), theme));

	}

	@GetMapping("/get-subquests")
	public ResponseEntity<ApiResponse<List<SubQuestResponseDto>>> getSubQuests(@CurrentUser BasicUsersDto user,
		@RequestParam List<Integer> attributes,
		@RequestParam Long mainQuest) {

		return ApiResponse.ok(subQuestService.getSubQuests(attributes, mainQuest, user.id()));

	}

	@PostMapping("/reroll-subquests")
	public ResponseEntity<ApiResponse<List<MainQuestResponseDto>>> rerollSubQuests(@CurrentUser BasicUsersDto user,
		@RequestBody RerollSubQuestRequestDto dto) {
		// mainQuestService.subQuestService(dto, user.id());
		return ApiResponse.ok(null);

	}

}
