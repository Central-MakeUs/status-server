package com.statoverflow.status.domain.quest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.master.entity.MainSubQuest;
import com.statoverflow.status.domain.master.entity.SubQuest;
import com.statoverflow.status.domain.quest.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.RerollSubQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.enums.FrequencyType;
import com.statoverflow.status.domain.quest.repository.MainSubQuestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubQuestServiceImpl implements SubQuestService {

	private final MainSubQuestRepository mainSubQuestRepository;
	private final QuestUtil questUtil;
	private final int REQUIRED_QUEST_CNT = 4;

	@Override
	public List<SubQuestResponseDto> getSubQuests(List<Integer> attributes, Long mainQuest, Long id) {

		// todo: 1. 이미 진행 중인 서브 퀘스트 ID 목록 가져오기

		// 2. 유저가 선택한 attributes를 bitmask로 변경
		int selectedAttributesBitmask = questUtil.calculateCombinedBitmask(attributes);
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


		// 4. MainSubQuest 엔티티를 SubQuestResponseDto로 변환
		List<SubQuestResponseDto> subQuestDtos = availableSubQuests.stream()
			.map(this::mapToDto)
			.collect(Collectors.toList());
		log.debug("SubQuestResponseDto 변환 완료. 변환된 서브 퀘스트 개수: {}", subQuestDtos.size());
		log.debug("변환된 메인 퀘스트 ID: {}", subQuestDtos.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		// 5. 리스트를 무작위로 섞고, 4개만 반환
		List<SubQuestResponseDto> selectedMainQuests = questUtil.selectRandoms(subQuestDtos, REQUIRED_QUEST_CNT);
		log.info("getSubQuests 메서드 완료. 최종 선택된 서브 퀘스트 개수: {}", selectedMainQuests.size());
		log.info("최종 선택된 서브 퀘스트 ID: {}", selectedMainQuests.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		return selectedMainQuests;

	}

	@Override
	public List<SubQuestResponseDto> rerollSubQuestRequestDto(RerollSubQuestRequestDto dto, Long id) {
		return List.of();
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
