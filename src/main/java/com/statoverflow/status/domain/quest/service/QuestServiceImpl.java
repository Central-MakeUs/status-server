package com.statoverflow.status.domain.quest.service;

import static com.statoverflow.status.global.error.ErrorType.*;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.master.entity.MainSubQuest;
import com.statoverflow.status.domain.master.entity.QuestTheme;
import com.statoverflow.status.domain.master.entity.SubQuest;
import com.statoverflow.status.domain.quest.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.MainQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.ThemeResponseDto;
import com.statoverflow.status.domain.quest.enums.FrequencyType;
import com.statoverflow.status.domain.quest.repository.MainQuestRepository;
import com.statoverflow.status.domain.quest.repository.MainSubQuestRepository;
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
	private final MainQuestRepository mainQuestRepository;
	private final Random random;
	private final MainSubQuestRepository mainSubQuestRepository;

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


		List<ThemeResponseDto> selectedThemes = selectRandoms(themeResponseDtos, 4);
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

	@Override
	public List<MainQuestResponseDto> getMainQuests(List<Integer> attributes, Long userId, Long theme) {
		log.info("getMainQuests 메서드 시작. userId: {}, themeId: {}, attributes: {}", userId, theme, attributes);

		// 1. 선택된 Attribute ID 목록을 비트마스크로 조합
		int selectedAttributesBitmask = calculateCombinedBitmask(attributes);
		log.debug("계산된 selectedAttributesBitmask: {}", selectedAttributesBitmask);

		// 2. DB에서 메인 퀘스트 필터링 및 조회
		List<MainQuest> allCandidateMainQuests =
			mainQuestRepository.findAllByThemeIdAndAttributes(theme, selectedAttributesBitmask);
		log.debug("DB 조회 결과 (allCandidateMainQuests) 개수: {}", allCandidateMainQuests.size());
		log.debug("DB 조회 결과 (allCandidateMainQuests) ID: {}", allCandidateMainQuests.stream().map(MainQuest::getId).collect(Collectors.toList()));

		// todo: 3. 진행중인 퀘스트 제외
		List<MainQuest> availableMainQuests = allCandidateMainQuests;
		log.debug("진행중인 퀘스트 제외 후 MainQuest 개수: {}", availableMainQuests.size());
		log.debug("진행중인 퀘스트 제외 후 MainQuest ID: {}", availableMainQuests.stream().map(MainQuest::getId).collect(Collectors.toList()));


		// 4. MainQuest 엔티티를 MainQuestResponseDto로 변환
		List<MainQuestResponseDto> mainQuestDtos = availableMainQuests.stream()
			.map(mainQuest -> new MainQuestResponseDto(mainQuest.getId(), mainQuest.getName())) // theme.getId() 사용
			.collect(Collectors.toList());
		log.debug("MainQuestResponseDto 변환 완료. 변환된 메인 퀘스트 개수: {}", mainQuestDtos.size());
		log.debug("변환된 메인 퀘스트 ID: {}", mainQuestDtos.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		// 5. 리스트를 무작위로 섞고, 4개만 반환
		List<MainQuestResponseDto> selectedMainQuests = selectRandoms(mainQuestDtos, 4);
		log.info("getMainQuests 메서드 완료. 최종 선택된 메인 퀘스트 개수: {}", selectedMainQuests.size());
		log.info("최종 선택된 메인 퀘스트 ID: {}", selectedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		return selectedMainQuests;
	}

	@Override
	public List<MainQuestResponseDto> rerollMainQuests(List<Integer> attributes, List<Long> mainQuestsToExclude, Long userId, Long themeId) {
		log.info("rerollMainQuests 메서드 시작. attributes: {}, mainQuestsToExclude: {}, userId: {}, themeId: {}", attributes, mainQuestsToExclude, userId, themeId);

		// 1. 선택된 Attribute ID 목록을 비트마스크로 조합
		int selectedAttributesBitmask = calculateCombinedBitmask(attributes);
		log.debug("rerollMainQuests: 계산된 selectedAttributesBitmask: {}", selectedAttributesBitmask);

		// 2. 제외할 메인 퀘스트 ID Set 생성
		Set<Long> excludeMainQuestIds = mainQuestsToExclude.stream().collect(Collectors.toSet());
		log.debug("rerollMainQuests: 제외할 메인 퀘스트 ID (Set): {}", excludeMainQuestIds);

		// 3. DB에서 모든 후보 메인 퀘스트 조회 (선택된 테마와 능력치 조건 만족)
		List<MainQuest> allCandidateMainQuests =
			mainQuestRepository.findAllByThemeIdAndAttributes(themeId, selectedAttributesBitmask);
		log.debug("rerollMainQuests: DB 조회 결과 (allCandidateMainQuests) 개수: {}", allCandidateMainQuests.size());
		log.debug("rerollMainQuests: DB 조회 결과 (allCandidateMainQuests) ID: {}", allCandidateMainQuests.stream().map(MainQuest::getId).collect(Collectors.toList()));

		// todo: 4. 진행중인 퀘스트 제외 - 기존 퀘스트 및 리롤 퀘스트 모두에 적용
		List<MainQuest> currentAvailableMainQuests = allCandidateMainQuests; // 현재는 필터링 없음
		log.debug("rerollMainQuests: 진행중인 퀘스트 제외 (미구현) 후 MainQuest 개수: {}", currentAvailableMainQuests.size());
		log.debug("rerollMainQuests: 진행중인 퀘스트 제외 (미구현) 후 MainQuest ID: {}", currentAvailableMainQuests.stream().map(MainQuest::getId).collect(Collectors.toList()));


		// 5. 제외할 퀘스트 ID를 기준으로 두 개의 리스트로 분리
		List<MainQuestResponseDto> nonExcludedMainQuests = currentAvailableMainQuests.stream()
			.filter(mainQuest -> !excludeMainQuestIds.contains(mainQuest.getId()))
			.map(mainQuest -> new MainQuestResponseDto(mainQuest.getId(), mainQuest.getName()))
			.collect(Collectors.toList());
		log.debug("rerollMainQuests: 제외되지 않은 메인 퀘스트 개수: {}", nonExcludedMainQuests.size());
		log.debug("rerollMainQuests: 제외되지 않은 메인 퀘스트 ID: {}", nonExcludedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));


		List<MainQuestResponseDto> excludedAndPotentiallyReusableMainQuests = currentAvailableMainQuests.stream()
			.filter(mainQuest -> excludeMainQuestIds.contains(mainQuest.getId()))
			.map(mainQuest -> new MainQuestResponseDto(mainQuest.getId(), mainQuest.getName()))
			.collect(Collectors.toList());
		log.debug("rerollMainQuests: 제외된 (재사용 가능성 있는) 메인 퀘스트 개수: {}", excludedAndPotentiallyReusableMainQuests.size());
		log.debug("rerollMainQuests: 제외된 (재사용 가능성 있는) 메인 퀘스트 ID: {}", excludedAndPotentiallyReusableMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));


		// 6. 중복이 아닌 퀘스트를 먼저 무작위로 섞음
		Collections.shuffle(nonExcludedMainQuests, random);
		log.debug("rerollMainQuests: 제외되지 않은 메인 퀘스트 셔플 후: {}", nonExcludedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		// 7. 제외된 퀘스트도 무작위로 섞음 (나중에 부족할 경우 보충하기 위함)
		Collections.shuffle(excludedAndPotentiallyReusableMainQuests, random);
		log.debug("rerollMainQuests: 제외된 메인 퀘스트 셔플 후: {}", excludedAndPotentiallyReusableMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));


		int requiredCount = 4;

		// 8. 중복이 아닌 퀘스트에서 필요한 개수만큼 우선적으로 선택하고, 부족한 개수는 제외된 퀘스트에서 보충
		List<MainQuestResponseDto> finalSelectedMainQuests = Stream.concat(
				nonExcludedMainQuests.stream(),
				excludedAndPotentiallyReusableMainQuests.stream()
			)
			.limit(requiredCount)
			.collect(Collectors.toList());

		log.info("rerollMainQuests 메서드 완료. 최종 선택된 메인 퀘스트 개수: {}", finalSelectedMainQuests.size());
		log.info("최종 선택된 메인 퀘스트 ID: {}", finalSelectedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		return finalSelectedMainQuests;
	}

	@Override
	public List<SubQuestResponseDto> getSubQuests(List<Integer> attributes, Long mainQuest, Long id) {

		// todo: 1. 이미 진행 중인 서브 퀘스트 ID 목록 가져오기

		// 2. 유저가 선택한 attributes를 bitmask로 변경
		int selectedAttributesBitmask = calculateCombinedBitmask(attributes);
		log.debug("getSubQuests: 계산된 selectedAttributesBitmask: {}", selectedAttributesBitmask);


		// 2. DB에서 서브 퀘스트 필터링 및 조회
		List<MainSubQuest> subQuests =
			mainSubQuestRepository.findAllByMainQuestIdAndAttributes(mainQuest, selectedAttributesBitmask);
		log.debug("DB 조회 결과 (subQuests) 개수: {}", subQuests.size());
		log.debug("DB 조회 결과 (subQuests) ID: {}", subQuests.stream().map(mainSubQuest
			-> mainSubQuest.getSubQuest().getId()).collect(Collectors.toList()));

		// todo: 3. 진행중인 퀘스트 제외
		List<MainSubQuest> availableSubQuests = subQuests;
		log.debug("진행중인 퀘스트 제외 후 MainSubQuest 개수: {}", availableSubQuests.size());
		log.debug("진행중인 퀘스트 제외 후 MainSubQuest ID: {}", availableSubQuests.stream().map(mainSubQuest
			-> mainSubQuest.getSubQuest().getId()).collect(Collectors.toList()));


		// 4. MainQuest 엔티티를 MainQuestResponseDto로 변환
		List<SubQuestResponseDto> subQuestDtos = availableSubQuests.stream()
			.map(this::mapToDto)
			.collect(Collectors.toList());
		log.debug("MainQuestResponseDto 변환 완료. 변환된 메인 퀘스트 개수: {}", subQuestDtos.size());
		log.debug("변환된 메인 퀘스트 ID: {}", subQuestDtos.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		// 5. 리스트를 무작위로 섞고, 4개만 반환
		List<SubQuestResponseDto> selectedMainQuests = selectRandoms(subQuestDtos, 4);
		log.info("getMainQuests 메서드 완료. 최종 선택된 메인 퀘스트 개수: {}", selectedMainQuests.size());
		log.info("최종 선택된 메인 퀘스트 ID: {}", selectedMainQuests.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		return selectedMainQuests;

	}

	// 비트마스크 계산 로직
	private int calculateCombinedBitmask(List<Integer> attributes) {
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
		return combinedBitmask;
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

		List<QuestTheme> questThemes = themeRepository.findAllByAttributes(combinedBitmask);
		log.debug("findAllByLinkedAttributeIntersection 결과: {} 개", questThemes.size());
		return questThemes;
	}

	private <T> List<T> selectRandoms(List<T> items, int count) {
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

	// MainSubQuest 엔티티와 SubQuest 엔티티를 조합하여 SubQuestResponseDto로 변환하는 헬퍼 메서드
	private SubQuestResponseDto mapToDto(MainSubQuest mainSubQuest) {
		SubQuest subQuest = mainSubQuest.getSubQuest();

		// 1. attributes 필드 생성 (AttributeDto.fromMainSubQuest 사용)
		List<AttributeDto> attributes = AttributeDto.fromEntity(mainSubQuest);

		// 2. frequencyType 생성 (랜덤으로 선택)
		String frequencyTypeDescription = FrequencyType.getRandomFrequencyType().getDescription();

		// 3. actionUnitType 및 actionUnitNum 가져오기
		String actionUnitTypeUnit = subQuest.getActionUnitType().getUnit();
		int actionUnitNumValue = subQuest.getActionUnitType().getDefaultCount();

		// 4. desc 필드 생성 (플레이스홀더 채우기)
		String rawName = subQuest.getName(); // 예: "기상 후 {0:d}분 동안 스마트폰 알림 확인 안 하기"
		log.info("rawName: {}", rawName);
		String formattedDesc = String.format(rawName, actionUnitNumValue);


		return new SubQuestResponseDto(
			subQuest.getId(),
			frequencyTypeDescription,
			actionUnitTypeUnit,
			actionUnitNumValue,
			attributes,
			formattedDesc
		);
	}
}