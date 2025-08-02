package com.statoverflow.status.domain.quest.controller;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.SubQuestLogDto;
import com.statoverflow.status.domain.quest.dto.request.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.request.RerollSubQuestRequestDto;
import com.statoverflow.status.domain.quest.service.interfaces.MainQuestService;
import com.statoverflow.status.domain.quest.service.interfaces.SubQuestService;
import com.statoverflow.status.domain.quest.service.interfaces.ThemeService;
import com.statoverflow.status.domain.quest.service.interfaces.UsersMainQuestService;
import com.statoverflow.status.domain.quest.service.interfaces.UsersSubQuestService;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.global.annotation.CurrentUser;
import com.statoverflow.status.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/quest")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "[퀘스트] Quest API", description = "퀘스트, 테마, 능력치 관련 API")
public class QuestController {

	private final ThemeService themeService;
	private final MainQuestService mainQuestService;
	private final SubQuestService subQuestService;
	private final UsersMainQuestService usersMainQuestService;
	private final UsersSubQuestService usersSubQuestService;

	@Operation(summary = "[퀘스트 생성 - 1] 랜덤 테마 목록 조회", description = "능력치 코드에 맞는 랜덤 테마 목록을 조회합니다.")
	@GetMapping("/get-themes")
	public ResponseEntity<ApiResponse<List<ThemeResponseDto>>> getThemes(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user,
		@Parameter(description = "능력치 코드 목록", required = true) @RequestParam List<Integer> attributes) {
		return ApiResponse.ok(themeService.getThemes(attributes));
	}

	@Operation(summary = "[퀘스트 생성 - 2] 테마 목록 재요청", description = "현재 테마 목록을 제외한 테마 목록을 다시 조회합니다.")
	@GetMapping("/reroll-themes")
	public ResponseEntity<ApiResponse<List<ThemeResponseDto>>> rerollThemes(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user,
		@Parameter(description = "능력치 코드 목록", required = true) @RequestParam List<Integer> attributes,
		@Parameter(description = "재요청할 테마 코드 목록", required = true) @RequestParam List<Integer> themes) {
		return ApiResponse.ok(themeService.rerollThemes(attributes, themes));
	}

	@Operation(summary = "[퀘스트 생성 - 3] 메인 퀘스트 목록 조회", description = "선택된 테마에 맞는 메인 퀘스트 목록을 조회합니다.")
	@GetMapping("/get-mainquests")
	public ResponseEntity<ApiResponse<List<MainQuestResponseDto>>> getMainQuests(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user,
		@Parameter(description = "능력치 코드 목록", required = true) @RequestParam List<Integer> attributes,
		@Parameter(description = "선택된 테마 코드", required = true) @RequestParam Long theme) {
		return ApiResponse.ok(mainQuestService.getMainQuests(attributes, user.id(), theme));
	}

	@Operation(summary = "[퀘스트 생성 - 4] 메인 퀘스트 목록 재요청", description = "선택된 테마에 맞는 메인 퀘스트 목록을 다시 조회합니다.")
	@GetMapping("/reroll-mainquests")
	public ResponseEntity<ApiResponse<List<MainQuestResponseDto>>> rerollMainQuests(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user,
		@Parameter(description = "능력치 코드 목록", required = true) @RequestParam List<Integer> attributes,
		@Parameter(description = "선택된 테마 코드", required = true) @RequestParam Long theme,
		@Parameter(description = "재요청할 메인 퀘스트 코드 목록", required = true) @RequestParam List<Long> mainQuests) {
		return ApiResponse.ok(mainQuestService.rerollMainQuests(attributes, mainQuests, user.id(), theme));
	}

	@Operation(summary = "[퀘스트 생성 - 5] 서브 퀘스트 목록 조회", description = "선택된 메인 퀘스트에 맞는 서브 퀘스트 목록을 조회합니다.")
	@GetMapping("/get-subquests")
	public ResponseEntity<ApiResponse<List<SubQuestResponseDto>>> getSubQuests(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user,
		@Parameter(description = "능력치 코드 목록", required = true) @RequestParam List<Integer> attributes,
		@Parameter(description = "선택된 메인 퀘스트 코드", required = true) @RequestParam Long mainQuest) {
		return ApiResponse.ok(subQuestService.getSubQuests(attributes, mainQuest, user.id()));
	}

	@Operation(summary = "[퀘스트 생성 - 6] 서브 퀘스트 목록 재요청", description = "선택된 서브 퀘스트 목록을 재요청하여 새로 받습니다.")
	@PostMapping("/reroll-subquests")
	public ResponseEntity<ApiResponse<List<SubQuestResponseDto>>> rerollSubQuests(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user,
		@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "재요청할 서브 퀘스트 정보", required = true)
		@RequestBody RerollSubQuestRequestDto dto) {
		return ApiResponse.ok(subQuestService.rerollSubQuestRequestDto(dto, user.id()));
	}

	@Operation(summary = "[퀘스트 생성 - 7] 퀘스트 생성", description = "선택된 메인/서브 퀘스트로 유저 퀘스트를 생성합니다.")
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<CreateQuestResponseDto>> createQuest(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user,
		@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "생성할 퀘스트 정보", required = true)
		@Valid @RequestBody CreateQuestRequestDto dto) {
		return ApiResponse.ok(usersMainQuestService.create(dto, user.id()));
	}

	@Operation(summary = "[퀘스트 조회 - 1] 오늘의 서브 퀘스트 조회", description = "오늘 인증 할 수 있는 모든 서브 퀘스트를 조회합니다.")
	@GetMapping("/today")
	public ResponseEntity<ApiResponse<List<SubQuestResponseDto.UsersSubQuestResponseDto>>> getTodaySubQuests(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user) {
		return ApiResponse.ok(usersSubQuestService.getTodaySubQuests(user.id()));
	}

	@Operation(summary = "[퀘스트 상세 조회 - 1] 메인 퀘스트 ID로 서브 퀘스트 조회", description = "특정 메인 퀘스트에 속한 인증할 수 있는 서브 퀘스트를 조회합니다.")
	@GetMapping("/{id}/today")
	public ResponseEntity<ApiResponse<List<SubQuestResponseDto.UsersSubQuestResponseDto>>> getTodaySubQuestsByMainQuestId(
		@Parameter(description = "메인 퀘스트 ID", required = true) @PathVariable Long id,
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user) {
		return ApiResponse.ok(usersSubQuestService.getTodaySubQuests(user.id(), id));
	}

	@Operation(summary = "메인 퀘스트 삭제", description = "특정 메인 퀘스트를 삭제합니다.")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<?>> deleteMainQuest(
		@Parameter(description = "삭제할 메인 퀘스트 ID", required = true) @PathVariable Long id,
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user) {
		usersMainQuestService.deleteMainQuest(id);
		return ApiResponse.noContent();
	}

	@Operation(summary = "[퀘스트 조회 - 2]나의 메인 퀘스트 목록 조회", description = "현재 유저가 진행 중인 모든 메인 퀘스트를 조회합니다.")
	@GetMapping("/me")
	public ResponseEntity<ApiResponse<List<UsersMainQuestResponseDto>>> getUsersMainQuests(
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user) {
		return ApiResponse.ok(usersMainQuestService.getUsersMainQuests(user.id()));
	}

	@Operation(summary = "[퀘스트 상세 조회 - 2] 메인 퀘스트 ID로 서브 퀘스트 완료 기록 조회", description = "특정 메인 퀘스트에 속한 모든 서브 퀘스트 완료 기록을 조회합니다.")
	@GetMapping("/{id}/history")
	public ResponseEntity<ApiResponse<List<QuestHistoryByDateDto>>> getSubQuestsLogsByMainQuestId(
		@Parameter(description = "메인 퀘스트 ID", required = true) @PathVariable Long id,
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user) {
		return ApiResponse.ok(usersSubQuestService.getSubQuestsLogs(user.id(), id));
	}

	@Operation(summary = "서브 퀘스트 완료", description = "서브 퀘스트를 완료 처리하고 경험치를 부여합니다.")
	@PostMapping("/sub")
	public ResponseEntity<ApiResponse<List<AttributeDto>>> doSubQuest(
		@RequestBody SubQuestLogDto dto,
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user) {

		DoSubQuestResponseDto res = usersSubQuestService.doSubQuest(user.id(), dto);

		// 메인퀘스트 완료 여부 체크
		usersSubQuestService.checkMainQuestCompleted(res.mainQuest());
		return ApiResponse.ok(res.returns());
	}

	@Operation(summary = "서브 퀘스트 완료 기록 수정", description = "서브 퀘스트 완료 기록을 수정합니다.")
	@PatchMapping("/sub")
	public ResponseEntity<ApiResponse<SubQuestLogDto>> editSubQuest(
		@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정할 서브 퀘스트 기록 정보", required = true)
		@RequestBody SubQuestLogDto dto,
		@Parameter(hidden = true) @CurrentUser BasicUsersDto user) {
		return ApiResponse.ok(usersSubQuestService.editSubQuest(user.id(), dto));
	}
}