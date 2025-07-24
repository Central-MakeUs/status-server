package com.statoverflow.status.domain.quest.service;

import static com.statoverflow.status.global.error.ErrorType.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.QuestTheme;
import com.statoverflow.status.domain.quest.dto.ThemeResponseDto;
import com.statoverflow.status.domain.quest.repository.ThemeRepository;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestServiceImpl implements QuestService{

	private final AttributeRepository attributeRepository;
	private final ThemeRepository themeRepository;
	private final Random random;

	@Override
	public List<ThemeResponseDto> getThemes(List<Integer> attributes) {
		log.info("getThemes 메서드 시작. 입력 attributes: {}", attributes);

		List<QuestTheme> allMatchingThemes = getAllMatchingThemesByAttributes(attributes);
		log.debug("getAllMatchingThemesByAttributes 결과. 조회된 QuestTheme 개수: {}", allMatchingThemes.size());
		log.debug("조회된 QuestTheme ID: {}", allMatchingThemes.stream().map(QuestTheme::getId).collect(Collectors.toList()));

		List<ThemeResponseDto> themeResponseDtos = allMatchingThemes.stream()
			// QuestTheme::getId()가 Long을 반환하므로, ThemeResponseDto도 Long 타입 ID를 받도록 가정
			.map(questTheme -> new ThemeResponseDto(questTheme.getId(), questTheme.getName()))
			.collect(Collectors.toList());
		log.debug("ThemeResponseDto 변환 완료. 변환된 테마 개수: {}", themeResponseDtos.size());
		log.debug("변환된 테마 ID: {}", themeResponseDtos.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));


		List<ThemeResponseDto> selectedThemes = selectRandomThemes(themeResponseDtos, 4);
		log.info("getThemes 메서드 완료. 최종 선택된 테마 개수: {}", selectedThemes.size());
		log.info("최종 선택된 테마 ID: {}", selectedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		return selectedThemes;
	}

	@Override
	public List<ThemeResponseDto> rerollThemes(List<Integer> attributes, List<Integer> themesToExclude) {
		log.info("rerollThemes 메서드 시작. 입력 attributes: {}, themesToExclude: {}", attributes, themesToExclude);

		List<QuestTheme> allMatchingThemes = getAllMatchingThemesByAttributes(attributes);
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
		log.debug("제외되지 않은 테마 개수: {}", nonExcludedThemes.size());
		log.debug("제외되지 않은 테마 ID: {}", nonExcludedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));


		List<ThemeResponseDto> excludedThemes = allMatchingThemes.stream()
			.filter(questTheme -> excludeThemeIds.contains(questTheme.getId()))
			.map(questTheme -> new ThemeResponseDto(questTheme.getId(), questTheme.getName()))
			.collect(Collectors.toList());
		log.debug("제외된 테마 개수: {}", excludedThemes.size());
		log.debug("제외된 테마 ID: {}", excludedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		// 3. 중복이 아닌 테마를 먼저 무작위로 섞음
		Collections.shuffle(nonExcludedThemes, random);
		log.debug("제외되지 않은 테마 셔플 후 (순서 변경): {}", nonExcludedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		// 중복된 테마도 무작위로 섞음
		Collections.shuffle(excludedThemes, random);
		log.debug("제외된 테마 셔플 후 (순서 변경): {}", excludedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));


		int requiredCount = 4;

		// 4. 중복이 아닌 테마에서 필요한 개수만큼 우선적으로 선택하고,
		// 부족한 개수는 중복된 테마에서 보충
		List<ThemeResponseDto> finalSelectedThemes = Stream.concat(
				nonExcludedThemes.stream(),
				excludedThemes.stream()
			)
			.limit(requiredCount)
			.collect(Collectors.toList());

		log.info("rerollThemes 메서드 완료. 최종 선택된 테마 개수: {}", finalSelectedThemes.size());
		log.info("최종 선택된 테마 ID: {}", finalSelectedThemes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		return finalSelectedThemes;
	}

	private List<QuestTheme> getAllMatchingThemesByAttributes(List<Integer> attributes) {
		log.debug("getAllMatchingThemesByAttributes 호출. 입력 attributes: {}", attributes);
		if (attributes == null || attributes.isEmpty() || attributes.size() > 2) {
			log.warn("INVALID_ATTRIBUTES: attributes가 유효하지 않습니다. 입력: {}", attributes);
			throw new CustomException(ErrorType.INVALID_ATTRIBUTES);
		}

		int combinedBitmask = attributes.stream()
			.mapToInt(attributeId ->
				attributeRepository.findById(attributeId)
					.map(Attribute::getBitMask)
					.orElseThrow(() -> {
						log.warn("INVALID_ATTRIBUTES: 존재하지 않는 attributeId 감지: {}", attributeId);
						return new CustomException(INVALID_ATTRIBUTES);
					})
			)
			.reduce(0, (acc, bitMask) -> acc | bitMask);
		log.debug("계산된 combinedBitmask: {}", combinedBitmask);

		List<QuestTheme> questThemes = themeRepository.findAllByLinkedAttributeIntersection(combinedBitmask);
		log.debug("findAllByLinkedAttributeIntersection 결과: {} 개", questThemes.size());
		return questThemes;
	}

	private List<ThemeResponseDto> selectRandomThemes(List<ThemeResponseDto> themes, int count) {
		log.debug("selectRandomThemes 호출. 대상 테마 개수: {}, 선택 개수: {}", themes.size(), count);
		Collections.shuffle(themes, random);
		log.debug("selectRandomThemes: 셔플 후 테마 ID: {}", themes.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));

		List<ThemeResponseDto> selected = themes.stream()
			.limit(count)
			.collect(Collectors.toList());
		log.debug("selectRandomThemes: 최종 선택된 테마 개수: {}, ID: {}", selected.size(), selected.stream().map(ThemeResponseDto::id).collect(Collectors.toList()));
		return selected;
	}
}