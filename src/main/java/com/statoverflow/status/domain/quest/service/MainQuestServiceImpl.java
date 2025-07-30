package com.statoverflow.status.domain.quest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.quest.dto.response.MainQuestResponseDto;
import com.statoverflow.status.domain.quest.repository.MainQuestRepository;
import com.statoverflow.status.domain.quest.service.interfaces.MainQuestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainQuestServiceImpl implements MainQuestService {

	private final MainQuestRepository mainQuestRepository;
	private final QuestUtil questUtil;

	@Value("${status.quest.mainquest.output_mainquest_num}")
	private int OUTPUT_MAINQUEST_NUM;


	@Override
	public List<MainQuestResponseDto> getMainQuests(List<Integer> attributes, Long userId, Long theme) {
		log.info("getMainQuests 메서드 시작. userId: {}, themeId: {}, attributes: {}", userId, theme, attributes);

		// 1. 선택된 Attribute ID 목록을 비트마스크로 조합
		int selectedAttributesBitmask = questUtil.calculateCombinedBitmask(attributes);
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
		List<MainQuestResponseDto> selectedMainQuests = questUtil.selectRandoms(mainQuestDtos, OUTPUT_MAINQUEST_NUM);
		log.info("getMainQuests 메서드 완료. 최종 선택된 메인 퀘스트 개수: {}", selectedMainQuests.size());
		log.info("최종 선택된 메인 퀘스트 ID: {}", selectedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		return selectedMainQuests;
	}

	@Override
	public List<MainQuestResponseDto> rerollMainQuests(List<Integer> attributes, List<Long> mainQuestsToExclude, Long userId, Long themeId) {
		log.info("rerollMainQuests 메서드 시작. attributes: {}, mainQuestsToExclude: {}, userId: {}, themeId: {}", attributes, mainQuestsToExclude, userId, themeId);

		// 1. 선택된 Attribute ID 목록을 비트마스크로 조합
		int selectedAttributesBitmask = questUtil.calculateCombinedBitmask(attributes);
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
		int nonExcludedSize = nonExcludedMainQuests.size();
		log.debug("rerollMainQuests: 제외되지 않은 메인 퀘스트 개수: {}", nonExcludedMainQuests.size());
		log.debug("rerollMainQuests: 제외되지 않은 메인 퀘스트 ID: {}", nonExcludedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));


		List<MainQuestResponseDto> excludedAndPotentiallyReusableMainQuests = currentAvailableMainQuests.stream()
			.filter(mainQuest -> excludeMainQuestIds.contains(mainQuest.getId()))
			.map(mainQuest -> new MainQuestResponseDto(mainQuest.getId(), mainQuest.getName()))
			.collect(Collectors.toList());
		int excludedSize = excludedAndPotentiallyReusableMainQuests.size();
		log.debug("rerollMainQuests: 제외된 (재사용 가능성 있는) 메인 퀘스트 개수: {}", excludedAndPotentiallyReusableMainQuests.size());
		log.debug("rerollMainQuests: 제외된 (재사용 가능성 있는) 메인 퀘스트 ID: {}", excludedAndPotentiallyReusableMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));


		List<MainQuestResponseDto> finalSelectedMainQuests = new ArrayList<>();
		// 6. 중복이 아닌 퀘스트 중 선택
		finalSelectedMainQuests.addAll(questUtil.selectRandoms(nonExcludedMainQuests, Math.min(nonExcludedSize, OUTPUT_MAINQUEST_NUM)));

		// 7. 제외된 퀘스트 중 선택
		if(nonExcludedSize < OUTPUT_MAINQUEST_NUM) {
			finalSelectedMainQuests.addAll(questUtil.selectRandoms(excludedAndPotentiallyReusableMainQuests, OUTPUT_MAINQUEST_NUM - nonExcludedSize));
		}

		log.info("rerollMainQuests 메서드 완료. 최종 선택된 메인 퀘스트 개수: {}", finalSelectedMainQuests.size());
		log.info("최종 선택된 메인 퀘스트 ID: {}", finalSelectedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		return finalSelectedMainQuests;
	}

}