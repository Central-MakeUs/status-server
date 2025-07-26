package com.statoverflow.status.domain.quest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.statoverflow.status.domain.quest.dto.request.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.request.RerollSubQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.CreateQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.MainQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.QuestHistoryByDateDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.ThemeResponseDto;
import com.statoverflow.status.domain.quest.dto.response.UsersMainQuestResponseDto;
import com.statoverflow.status.domain.quest.service.interfaces.MainQuestService;
import com.statoverflow.status.domain.quest.service.interfaces.SubQuestService;
import com.statoverflow.status.domain.quest.service.interfaces.ThemeService;
import com.statoverflow.status.domain.quest.service.interfaces.UsersMainQuestService;
import com.statoverflow.status.domain.quest.service.interfaces.UsersSubQuestService;
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
	private final UsersMainQuestService usersMainQuestService;
	private final UsersSubQuestService usersSubQuestService;

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
	public ResponseEntity<ApiResponse<List<SubQuestResponseDto>>> rerollSubQuests(@CurrentUser BasicUsersDto user,
		@RequestBody RerollSubQuestRequestDto dto) {
		return ApiResponse.ok(subQuestService.rerollSubQuestRequestDto(dto, user.id()));

	}

	@PostMapping("/create")
	public ResponseEntity<ApiResponse<CreateQuestResponseDto>> createQuest(@CurrentUser BasicUsersDto user,
		@RequestBody CreateQuestRequestDto dto) {
		return ApiResponse.ok(usersMainQuestService.create(dto, user.id()));

	}

	@GetMapping("/today")
	public ResponseEntity<ApiResponse<List<SubQuestResponseDto.UsersSubQuestResponseDto>>> getTodaySubQuests(
			@CurrentUser BasicUsersDto user) {

		return ApiResponse.ok(usersSubQuestService.getTodaySubQuests(user.id()));

	}

	@GetMapping("/{id}/today")
	public ResponseEntity<ApiResponse<List<SubQuestResponseDto.UsersSubQuestResponseDto>>> getTodaySubQuestsByMainQuestId(
		@PathVariable Long id, @CurrentUser BasicUsersDto user) {

		return ApiResponse.ok(usersSubQuestService.getTodaySubQuests(user.id(), id));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteMainQuest(@PathVariable Long id, @CurrentUser BasicUsersDto user) {
		usersMainQuestService.deleteMainQuest(id);
		return ApiResponse.noContent();
	}

	@GetMapping("/me")
	public ResponseEntity<ApiResponse<List<UsersMainQuestResponseDto>>> getUsersMainQuests(@CurrentUser BasicUsersDto user) {
		return ApiResponse.ok(usersMainQuestService.getUsersMainQuests(user.id()));
	}

	@GetMapping("/{id}/history")
	public ResponseEntity<ApiResponse<List<QuestHistoryByDateDto>>> getSubQuestsLogsByMainQuestId(
		@PathVariable Long id, @CurrentUser BasicUsersDto user) {

		return ApiResponse.ok(usersSubQuestService.getSubQuestsLogs(user.id(), id));

	}

}
