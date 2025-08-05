package com.statoverflow.status.domain.quest.service;

import static com.statoverflow.status.global.error.ErrorType.*;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.QuestTheme;
import com.statoverflow.status.domain.quest.repository.ThemeRepository;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class QuestUtil {

	private final AttributeRepository attributeRepository;
	private final ThemeRepository themeRepository;
	private final Random random;

	@Value("${status.quest.attribute.select_attribute_num}")
	private int SELECTED_ATTRIBUTE_NUM;

	// 비트마스크 계산 로직
	int calculateCombinedBitmask(List<Integer> attributes) {
		if (attributes == null || attributes.isEmpty() || attributes.size() > SELECTED_ATTRIBUTE_NUM) {
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
		return combinedBitmask;
	}

	List<QuestTheme> getAllMatchingThemesByAttributes(List<Integer> attributes) {
		log.debug("getAllMatchingThemesByAttributes 호출. 입력 attributes: {}", attributes);
		if (attributes == null || attributes.isEmpty() || attributes.size() > SELECTED_ATTRIBUTE_NUM) {
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

		List<QuestTheme> questThemes = themeRepository.findAllByAttributes(combinedBitmask);
		log.debug("findAllByLinkedAttributeIntersection 결과: {} 개", questThemes.size());
		return questThemes;
	}

	<T> List<T> selectRandoms(List<T> items, int count) {
		log.debug("selectRandoms 호출. 대상 메인 퀘스트 개수: {}, 선택 개수: {}", items.size(), count);
		if (items.isEmpty()) {
			log.debug("selectRandoms: 입력 메인 퀘스트 리스트가 비어있습니다. 빈 리스트 반환.");
			return Collections.emptyList();
		}
		Collections.shuffle(items, random);
		// log.debug("selectRandomMainQuests: 셔플 후 메인 퀘스트 ID: {}", mainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		List<T> selected = items.stream()
			.limit(count)
			.collect(Collectors.toList());
		// log.debug("selectRandomMainQuests: 최종 선택된 메인 퀘스트 개수: {}, ID: {}", selected.size(), selected.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));
		return selected;
	}
}
