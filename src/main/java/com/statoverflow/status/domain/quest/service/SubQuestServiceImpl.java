package com.statoverflow.status.domain.quest.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.master.entity.MainSubQuest;
import com.statoverflow.status.domain.master.entity.SubQuest;
import com.statoverflow.status.domain.quest.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.request.RerollSubQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;
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
		log.debug("변환된 서브 퀘스트 ID: {}", subQuestDtos.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		// 5. 리스트를 무작위로 섞고, 4개만 반환
		List<SubQuestResponseDto> selectedMainQuests = questUtil.selectRandoms(subQuestDtos, REQUIRED_QUEST_CNT);
		log.info("getSubQuests 메서드 완료. 최종 선택된 서브 퀘스트 개수: {}", selectedMainQuests.size());
		log.info("최종 선택된 서브 퀘스트 ID: {}", selectedMainQuests.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		return selectedMainQuests;

	}

	@Override
	public List<SubQuestResponseDto> rerollSubQuestRequestDto(RerollSubQuestRequestDto dto, Long id) {

		int reroll_required_quest_cnt = REQUIRED_QUEST_CNT - dto.selectedSubQuests().size();

		// 2. 유저가 선택한 attributes를 bitmask로 변경
		int selectedAttributesBitmask = questUtil.calculateCombinedBitmask(dto.attributes());
		log.debug("getSubQuests: 계산된 selectedAttributesBitmask: {}", selectedAttributesBitmask);

		// 2. DB에서 서브 퀘스트 필터링 및 조회
		List<MainSubQuest> subQuests =
			mainSubQuestRepository.findAllByMainQuestIdAndAttributes(dto.mainQuest(), selectedAttributesBitmask);
		log.debug("DB 조회 결과 (subQuests) 개수: {}", subQuests.size());
		log.debug("DB 조회 결과 (subQuests) ID: {}", subQuests.stream().map(mainSubQuest
			-> mainSubQuest.getSubQuest().getId()).collect(Collectors.toList()));

		// todo: 3. 진행중인 퀘스트 제외
		List<MainSubQuest> availableSubQuests = subQuests;
		// log.debug("진행중인 퀘스트 제외 후 MainSubQuest 개수: {}", availableSubQuests.size());
		// log.debug("진행중인 퀘스트 제외 후 MainSubQuest ID: {}", availableSubQuests.stream().map(mainSubQuest
		// 	-> mainSubQuest.getSubQuest().getId()).collect(Collectors.toList()));

		// 4. MainSubQuest 엔티티를 SubQuestResponseDto로 변환
		List<SubQuestResponseDto> subQuestDtos = availableSubQuests.stream()
			.map(this::mapToDto)
			.collect(Collectors.toList());
		log.debug("SubQuestResponseDto 변환 완료. 변환된 서브 퀘스트 개수: {}", subQuestDtos.size());
		log.debug("변환된 서브 퀘스트 ID: {}", subQuestDtos.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		// 5. selectedSubQuests 제외
		Set<Long> excludeIds = new HashSet<>();

		if (dto.selectedSubQuests() != null) {
			excludeIds.addAll(dto.selectedSubQuests()); // selectedSubQuests는 항상 제외
		}

		List<SubQuestResponseDto> primaryRandomPool = subQuestDtos.stream()
			.filter(sqDto -> !excludeIds.contains(sqDto.id())) // selectedSubQuests 제외
			.collect(Collectors.toList());
		log.debug("Primary 랜덤 풀 (selected & gotten 제외) 개수: {}", primaryRandomPool.size());
		log.debug("Primary 랜덤 풀 ID: {}", primaryRandomPool.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));


		// 6. gottenSubQuests , 제외한 List 출력
		Set<Long> gottenSubQuestIds = dto.gottenSubQuests().stream().collect(Collectors.toSet());

		// 5. 제외할 퀘스트 ID를 기준으로 두 개의 리스트로 분리
		List<SubQuestResponseDto> nonExcludedSubQuests = primaryRandomPool.stream()
			.filter(subQuest -> !gottenSubQuestIds.contains(subQuest.id()))
			.collect(Collectors.toList());
		int nonExcludedSize = nonExcludedSubQuests.size();
		log.debug("rerollSubQuests: 제외되지 않은 서브 퀘스트 개수: {}", nonExcludedSubQuests.size());
		log.debug("rerollSubQuests: 제외되지 않은 서브 퀘스트 ID: {}", nonExcludedSubQuests.stream().collect(Collectors.toList()));

		List<SubQuestResponseDto> excludedAndPotentiallyReusableSubQuests = primaryRandomPool.stream()
			.filter(subQuest -> gottenSubQuestIds.contains(subQuest.id()))
			.collect(Collectors.toList());
		int excludedSize = excludedAndPotentiallyReusableSubQuests.size();
		log.debug("rerollSubQuests: 제외된 (재사용 가능성 있는) 서브 퀘스트 개수: {}", excludedAndPotentiallyReusableSubQuests.size());
		log.debug("rerollSubQuests: 제외된 (재사용 가능성 있는) 서브 퀘스트 ID: {}", excludedAndPotentiallyReusableSubQuests
			.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		List<SubQuestResponseDto> finalSelectedSubQuests = new ArrayList<>();
		// 6. 중복이 아닌 퀘스트 중 선택
		finalSelectedSubQuests.addAll(questUtil.selectRandoms(nonExcludedSubQuests,
			Math.min(nonExcludedSize, reroll_required_quest_cnt)));

		// 7. 제외된 퀘스트 중 선택
		if(nonExcludedSize < reroll_required_quest_cnt) {
			finalSelectedSubQuests.addAll(questUtil.selectRandoms(excludedAndPotentiallyReusableSubQuests,
				reroll_required_quest_cnt - nonExcludedSize));
		}

		log.info("rerollMainQuests 메서드 완료. 최종 선택된 메인 퀘스트 개수: {}", finalSelectedSubQuests.size());
		log.info("최종 선택된 메인 퀘스트 ID: {}", finalSelectedSubQuests.stream().map(SubQuestResponseDto::id).collect(Collectors.toList()));

		return finalSelectedSubQuests;

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
