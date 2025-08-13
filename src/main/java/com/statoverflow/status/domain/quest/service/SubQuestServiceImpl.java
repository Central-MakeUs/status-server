package com.statoverflow.status.domain.quest.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.master.entity.MainSubQuest;
import com.statoverflow.status.domain.master.entity.SubQuest;
import com.statoverflow.status.domain.quest.dto.request.RerollSubQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.enums.FrequencyType;
import com.statoverflow.status.domain.quest.repository.MainSubQuestRepository;
import com.statoverflow.status.domain.quest.service.interfaces.SubQuestService;
import com.statoverflow.status.domain.quest.service.interfaces.UsersSubQuestService;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubQuestServiceImpl implements SubQuestService {

	private final MainSubQuestRepository mainSubQuestRepository;
	private final UsersSubQuestService usersSubQuestService;
	private final QuestUtil questUtil;

	@Value("${status.quest.subquest.output_subquest_num}")
	private int OUTPUT_SUBQUEST_NUM;

	@Value("${status.quest.subquest.select_subquest_num}")
	private int SELECT_SUBQUEST_NUM;

	@Override
	public List<SubQuestResponseDto> getSubQuests(List<Integer> attributes, Long mainQuest, Long userId) {
		log.info("getSubQuests 호출: mainQuest={}, userId={}, attributes={}", mainQuest, userId, attributes);

		// TODO: 1. 이미 진행 중인 서브 퀘스트 ID 목록 가져오기
		List<SubQuestResponseDto> availableSubQuests = getAvailableSubQuests(attributes, mainQuest, userId);

		// 무작위로 섞고, 지정된 개수만 반환
		List<SubQuestResponseDto> selectedSubQuests = questUtil.selectRandoms(availableSubQuests, OUTPUT_SUBQUEST_NUM);

		log.info("getSubQuests 완료: 최종 선택된 서브 퀘스트 개수={}, ID={}",
			selectedSubQuests.size(),
			selectedSubQuests.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		return selectedSubQuests;
	}

	@Override
	public List<SubQuestResponseDto> rerollSubQuestRequestDto(RerollSubQuestRequestDto dto, Long userId) {
		log.info("rerollSubQuests 호출: userId={}, selectedCount={}", userId, dto.selectedSubQuests().size());

		validateRerollRequest(dto);

		int rerollRequiredCount = OUTPUT_SUBQUEST_NUM - dto.selectedSubQuests().size();
		List<SubQuestResponseDto> availableSubQuests = getAvailableSubQuests(dto.attributes(), dto.mainQuest(), userId);

		// 선택된 퀘스트 제외
		List<SubQuestResponseDto> candidateSubQuests = excludeSelectedSubQuests(availableSubQuests, dto.selectedSubQuests());

		// 우선순위에 따라 퀘스트 선택
		List<SubQuestResponseDto> finalSelectedSubQuests = selectQuestsWithPriority(
			candidateSubQuests, dto.gottenSubQuests(), rerollRequiredCount);

		log.info("rerollSubQuests 완료: 최종 선택된 서브 퀘스트 개수={}, ID={}",
			finalSelectedSubQuests.size(),
			finalSelectedSubQuests.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		return finalSelectedSubQuests;
	}

	/**
	 * 사용 가능한 서브 퀘스트 목록을 조회하고 DTO로 변환
	 */
	private List<SubQuestResponseDto> getAvailableSubQuests(List<Integer> attributes, Long mainQuest, Long userId) {
		// 속성을 비트마스크로 변환
		int selectedAttributesBitmask = questUtil.calculateCombinedBitmask(attributes);
		log.debug("계산된 selectedAttributesBitmask: {}", selectedAttributesBitmask);

		// DB에서 서브 퀘스트 조회
		List<MainSubQuest> subQuests = mainSubQuestRepository.findAllByMainQuestIdAndAttributes(mainQuest, selectedAttributesBitmask);
		log.debug("DB 조회 결과 개수: {}, ID: {}",
			subQuests.size(),
			subQuests.stream().map(mq -> mq.getSubQuest().getId()).collect(Collectors.toList()));

		// TODO: 진행중인 퀘스트 제외 로직 구현
		List<MainSubQuest> availableSubQuests = subQuests;
		log.debug("진행중인 퀘스트 제외 후 개수: {}", availableSubQuests.size());

		// DTO로 변환
		List<SubQuestResponseDto> subQuestDtos = availableSubQuests.stream()
			.map(this::mapToDto)
			.collect(Collectors.toList());

		log.debug("DTO 변환 완료: 개수={}", subQuestDtos.size());
		return subQuestDtos;
	}

	/**
	 * Reroll 요청 유효성 검증
	 */
	private void validateRerollRequest(RerollSubQuestRequestDto dto) {
		int selectedCount = dto.selectedSubQuests().size();
		if (selectedCount >= OUTPUT_SUBQUEST_NUM) {
			log.warn("잘못된 reroll 요청: 선택된 퀘스트 수가 출력 개수 이상 ({}>={})", selectedCount, OUTPUT_SUBQUEST_NUM);
			throw new CustomException(ErrorType.INVALID_SUBQUEST_SELECTED);
		}
	}

	/**
	 * 선택된 서브 퀘스트를 제외한 후보 목록 반환
	 */
	private List<SubQuestResponseDto> excludeSelectedSubQuests(List<SubQuestResponseDto> allSubQuests, List<Long> selectedSubQuests) {
		Set<Long> excludeIds = new HashSet<>(selectedSubQuests);

		List<SubQuestResponseDto> candidates = allSubQuests.stream()
			.filter(quest -> !excludeIds.contains(quest.id()))
			.collect(Collectors.toList());

		log.debug("선택된 퀘스트 제외 후 후보 개수: {}", candidates.size());
		return candidates;
	}

	/**
	 * 우선순위에 따라 퀘스트 선택 (이전에 받지 않은 퀘스트 우선, 부족하면 받은 퀘스트에서 선택)
	 */
	private List<SubQuestResponseDto> selectQuestsWithPriority(
		List<SubQuestResponseDto> candidates, List<Long> gottenSubQuests, int requiredCount) {

		Set<Long> gottenIds = new HashSet<>(gottenSubQuests);

		// 우선순위 그룹 분리
		List<SubQuestResponseDto> newQuests = candidates.stream()
			.filter(quest -> !gottenIds.contains(quest.id()))
			.collect(Collectors.toList());

		List<SubQuestResponseDto> reusableQuests = candidates.stream()
			.filter(quest -> gottenIds.contains(quest.id()))
			.collect(Collectors.toList());

		log.debug("새로운 퀘스트 개수: {}, 재사용 가능한 퀘스트 개수: {}", newQuests.size(), reusableQuests.size());

		List<SubQuestResponseDto> finalSelected = new ArrayList<>();

		// 새로운 퀘스트 우선 선택
		int newQuestCount = Math.min(newQuests.size(), requiredCount);
		finalSelected.addAll(questUtil.selectRandoms(newQuests, newQuestCount));

		// 부족한 만큼 재사용 퀘스트에서 선택
		int remainingCount = requiredCount - newQuestCount;
		if (remainingCount > 0) {
			finalSelected.addAll(questUtil.selectRandoms(reusableQuests, remainingCount));
		}

		return finalSelected;
	}

	/**
	 * MainSubQuest 엔티티를 SubQuestResponseDto로 변환
	 */
	private SubQuestResponseDto mapToDto(MainSubQuest mainSubQuest) {
		SubQuest subQuest = mainSubQuest.getSubQuest();

		// 속성 정보 생성
		List<AttributeDto> attributes = AttributeDto.fromMainSubQuest(mainSubQuest);

		// 랜덤 빈도 타입 선택
		FrequencyType frequencyType = FrequencyType.getRandomFrequencyType();

		// 액션 단위 정보 추출
		String actionUnitTypeUnit = subQuest.getActionUnitType().getUnit();
		int actionUnitNumValue = subQuest.getActionUnitType().getDefaultCount();

		// 설명 필드 생성 (플레이스홀더 치환)
		String formattedDesc = String.format(subQuest.getName(), actionUnitNumValue);
		log.debug("퀘스트 설명 변환: '{}' -> '{}'", subQuest.getName(), formattedDesc);

		return new SubQuestResponseDto(
			subQuest.getId(),
			frequencyType,
			actionUnitTypeUnit,
			actionUnitNumValue,
			attributes,
			formattedDesc
		);
	}
}