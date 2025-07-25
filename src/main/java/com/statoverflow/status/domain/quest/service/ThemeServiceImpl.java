package com.statoverflow.status.domain.quest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.master.entity.QuestTheme;
import com.statoverflow.status.domain.quest.dto.response.ThemeResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThemeServiceImpl implements ThemeService {

	private final int REQUIRED_THEME_CNT = 4;
	private final QuestUtil questUtil;

	@Override
	public List<ThemeResponseDto> getThemes(List<Integer> attributes) {
		log.info("getThemes 메서드 시작. 입력 attributes: {}", attributes);

		List<QuestTheme> allMatchingThemes = questUtil.getAllMatchingThemesByAttributes(attributes);
		log.debug("getAllMatchingThemesByAttributes 결과. 조회된 QuestTheme 개수: {}", allMatchingThemes.size());
		log.debug("조회된 QuestTheme ID: {}", allMatchingThemes.stream().map(QuestTheme::getId).collect(Collectors.toList()));

		List<ThemeResponseDto> themeResponseDtos = allMatchingThemes.stream()
			// QuestTheme::getId()가 Long을 반환하므로, ThemeResponseDto도 Long 타입 ID를 받도록 가정
			.map(questTheme -> new ThemeResponseDto(questTheme.getId(), questTheme.getName()))
			.collect(Collectors.toList());
		log.debug("ThemeResponseDto 변환 완료. 변환된 테마 개수: {}", themeResponseDtos.size());
		log.debug("변환된 테마 ID: {}", themeResponseDtos.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));


		List<ThemeResponseDto> selectedThemes = questUtil.selectRandoms(themeResponseDtos, REQUIRED_THEME_CNT);
		log.info("getThemes 메서드 완료. 최종 선택된 테마 개수: {}", selectedThemes.size());
		log.info("최종 선택된 테마 ID: {}", selectedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		return selectedThemes;
	}

	@Override
	public List<ThemeResponseDto> rerollThemes(List<Integer> attributes, List<Integer> themesToExclude) {
		log.info("rerollThemes 메서드 시작. 입력 attributes: {}, themesToExclude: {}", attributes, themesToExclude);

		List<QuestTheme> allMatchingThemes = questUtil.getAllMatchingThemesByAttributes(attributes);
		log.debug("getAllMatchingThemesByAttributes 결과. 조회된 QuestTheme 개수: {}", allMatchingThemes.size());
		log.debug("조회된 QuestTheme ID: {}", allMatchingThemes.stream().map(QuestTheme::getId).collect(Collectors.toList()));

		// **핵심 수정 부분:** themesToExclude (Integer 리스트)를 Set<Long>으로 변환
		Set<Long> excludeThemeIds = themesToExclude.stream()
			.map(Integer::longValue)
			.collect(Collectors.toSet());
		log.debug("제외할 테마 ID (Set): {}", excludeThemeIds);

		// 2. 테마를 제외/포함 여부에 따라 두 개의 리스트로 분리
		List<ThemeResponseDto> nonExcludedThemes = allMatchingThemes.stream()
			.filter(questTheme -> !excludeThemeIds.contains(questTheme.getId()))
			.map(questTheme -> new ThemeResponseDto(questTheme.getId(), questTheme.getName()))
			.collect(Collectors.toList());
		int nonExcludedThemeCnt = nonExcludedThemes.size();
		log.debug("제외되지 않은 테마 개수: {}", nonExcludedThemeCnt);
		log.debug("제외되지 않은 테마 ID: {}", nonExcludedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		List<ThemeResponseDto> excludedThemes = allMatchingThemes.stream()
			.filter(questTheme -> excludeThemeIds.contains(questTheme.getId()))
			.map(questTheme -> new ThemeResponseDto(questTheme.getId(), questTheme.getName()))
			.collect(Collectors.toList());
		int excludedThemeCnt = excludedThemes.size();
		log.debug("제외된 테마 개수: {}", excludedThemeCnt);
		log.debug("제외된 테마 ID: {}", excludedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		List<ThemeResponseDto> finalSelectedThemes = new ArrayList<>();
		// 3. 중복이 아닌 테마를 먼저 무작위로 섞음
		finalSelectedThemes.addAll(questUtil.selectRandoms(
			nonExcludedThemes, Math.min(nonExcludedThemeCnt, REQUIRED_THEME_CNT)));

		if(nonExcludedThemeCnt<REQUIRED_THEME_CNT) {
			finalSelectedThemes.addAll(questUtil.selectRandoms(excludedThemes,
				REQUIRED_THEME_CNT-nonExcludedThemeCnt));
		}

		log.info("rerollThemes 메서드 완료. 최종 선택된 테마 개수: {}", finalSelectedThemes.size());
		log.info("최종 선택된 테마 ID: {}", finalSelectedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		return finalSelectedThemes;
	}
}
