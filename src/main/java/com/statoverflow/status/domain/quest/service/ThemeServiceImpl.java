package com.statoverflow.status.domain.quest.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream; // Stream 임포트 추가

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.master.entity.QuestTheme;
import com.statoverflow.status.domain.quest.dto.response.ThemeResponseDto;
import com.statoverflow.status.domain.quest.service.interfaces.ThemeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThemeServiceImpl implements ThemeService {

	@Value("${status.quest.theme.output_theme_num}")
	private int OUTPUT_THEME_NUM;

	private final QuestUtil questUtil;

	@Override
	public List<ThemeResponseDto> getThemes(List<Integer> attributes) {
		log.info("getThemes 메서드 시작. 입력 attributes: {}", attributes);

		// 공통 로직 호출
		List<ThemeResponseDto> allCandidateThemeDtos = getCandidateThemeDtos(attributes);
		log.debug("getAllMatchingThemesByAttributes 결과. 조회된 ThemeResponseDto 개수: {}", allCandidateThemeDtos.size());
		log.debug("조회된 ThemeResponseDto ID: {}", allCandidateThemeDtos.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		List<ThemeResponseDto> selectedThemes = questUtil.selectRandoms(allCandidateThemeDtos, OUTPUT_THEME_NUM);
		log.info("getThemes 메서드 완료. 최종 선택된 테마 개수: {}", selectedThemes.size());
		log.info("최종 선택된 테마 ID: {}", selectedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		return selectedThemes;
	}

	@Override
	public List<ThemeResponseDto> rerollThemes(List<Integer> attributes, List<Integer> themesToExclude) {
		log.info("rerollThemes 메서드 시작. 입력 attributes: {}, themesToExclude: {}", attributes, themesToExclude);

		// 1. 모든 후보 테마 DTO 조회 (공통 로직 호출)
		List<ThemeResponseDto> allCandidateThemeDtos = getCandidateThemeDtos(attributes);
		log.debug("rerollThemes: 모든 후보 ThemeResponseDto 개수: {}", allCandidateThemeDtos.size());
		log.debug("rerollThemes: 모든 후보 ThemeResponseDto ID: {}", allCandidateThemeDtos.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		// 2. 제외할 테마 ID Set 생성 (Integer -> Long 변환)
		Set<Long> excludeThemeIds = themesToExclude.stream()
			.map(Integer::longValue) // Integer ID를 Long ID로 변환
			.collect(Collectors.toSet());
		log.debug("rerollThemes: 제외할 테마 ID (Set): {}", excludeThemeIds);

		// 3. 리롤 시 제외할 테마를 필터링
		List<ThemeResponseDto> filteredForRerollThemes = allCandidateThemeDtos.stream()
			.filter(themeDto -> !excludeThemeIds.contains(themeDto.id()))
			.collect(Collectors.toList());
		log.debug("rerollThemes: 리롤 제외 후 남은 테마 개수: {}", filteredForRerollThemes.size());
		log.debug("rerollThemes: 리롤 제외 후 남은 테마 ID: {}", filteredForRerollThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		// 4. 남은 테마가 충분하지 않으면 제외된 테마 중 일부를 재사용
		List<ThemeResponseDto> finalSelectedThemes;
		if (filteredForRerollThemes.size() >= OUTPUT_THEME_NUM) {
			// 충분하면 리롤 필터링된 테마 중에서만 선택
			finalSelectedThemes = questUtil.selectRandoms(filteredForRerollThemes, OUTPUT_THEME_NUM);
		} else {
			// 부족하면 제외된 테마 중 재사용 가능한 테마를 찾아 합치기
			List<ThemeResponseDto> excludedReusableThemes = allCandidateThemeDtos.stream()
				.filter(themeDto -> excludeThemeIds.contains(themeDto.id()))
				.collect(Collectors.toList());

			// 리롤 후 남은 테마와 재사용 가능한 제외된 테마를 합쳐서 랜덤 선택
			List<ThemeResponseDto> combinedThemes = Stream.concat(
				filteredForRerollThemes.stream(),
				excludedReusableThemes.stream()
			).collect(Collectors.toList());

			finalSelectedThemes = questUtil.selectRandoms(combinedThemes, OUTPUT_THEME_NUM);
		}

		log.info("rerollThemes 메서드 완료. 최종 선택된 테마 개수: {}", finalSelectedThemes.size());
		log.info("최종 선택된 테마 ID: {}", finalSelectedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		return finalSelectedThemes;
	}

	/**
	 * 공통 로직: 선택된 attributes를 기준으로 모든 매칭되는 QuestTheme을 조회하고 ThemeResponseDto로 변환합니다.
	 */
	private List<ThemeResponseDto> getCandidateThemeDtos(List<Integer> attributes) {
		List<QuestTheme> allMatchingThemes = questUtil.getAllMatchingThemesByAttributes(attributes);
		log.debug("getAllMatchingThemesByAttributes 결과. 조회된 QuestTheme 개수: {}", allMatchingThemes.size());
		log.debug("조회된 QuestTheme ID: {}", allMatchingThemes.stream().map(QuestTheme::getId).collect(Collectors.toList()));

		List<ThemeResponseDto> themeResponseDtos = allMatchingThemes.stream()
			.map(questTheme -> new ThemeResponseDto(questTheme.getId(), questTheme.getName()))
			.collect(Collectors.toList());
		log.debug("ThemeResponseDto 변환 완료. 변환된 테마 개수: {}", themeResponseDtos.size());
		log.debug("변환된 테마 ID: {}", themeResponseDtos.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		return themeResponseDtos;
	}
}