package com.statoverflow.status.domain.quest.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream; // Stream 임포트 추가

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.quest.dto.response.MainQuestResponseDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.repository.MainQuestRepository;
import com.statoverflow.status.domain.quest.service.interfaces.MainQuestService;
import com.statoverflow.status.domain.quest.service.interfaces.UsersMainQuestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainQuestServiceImpl implements MainQuestService {

	private final MainQuestRepository mainQuestRepository;
	private final QuestUtil questUtil;
	private final UsersMainQuestService usersMainQuestService;

	@Value("${status.quest.mainquest.output_mainquest_num}")
	private int OUTPUT_MAINQUEST_NUM;

	@Override
	public List<MainQuestResponseDto> getMainQuests(List<Integer> attributes, Long userId, Long themeId) {
		log.info("getMainQuests 메서드 시작. userId: {}, themeId: {}, attributes: {}", userId, themeId, attributes);

		// 공통 로직 호출
		List<MainQuestResponseDto> availableMainQuestDtos = getAvailableMainQuestDtos(attributes, userId, themeId);
		log.debug("진행중인 퀘스트 제외 후 MainQuest 개수: {}", availableMainQuestDtos.size());
		log.debug("진행중인 퀘스트 제외 후 MainQuest ID: {}", availableMainQuestDtos.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));


		// 5. 리스트를 무작위로 섞고, OUTPUT_MAINQUEST_NUM 개만 반환
		List<MainQuestResponseDto> selectedMainQuests = questUtil.selectRandoms(availableMainQuestDtos, OUTPUT_MAINQUEST_NUM);
		log.info("getMainQuests 메서드 완료. 최종 선택된 메인 퀘스트 개수: {}", selectedMainQuests.size());
		log.info("최종 선택된 메인 퀘스트 ID: {}", selectedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		return selectedMainQuests;
	}

	@Override
	public List<MainQuestResponseDto> rerollMainQuests(List<Integer> attributes, List<Long> mainQuestsToExclude, Long userId, Long themeId) {
		log.info("rerollMainQuests 메서드 시작. attributes: {}, mainQuestsToExclude: {}, userId: {}, themeId: {}", attributes, mainQuestsToExclude, userId, themeId);

		// 1-4. 공통 로직 호출: 진행중인 퀘스트 제외된 후보 퀘스트 목록 가져오기
		List<MainQuestResponseDto> availableMainQuestDtos = getAvailableMainQuestDtos(attributes, userId, themeId);
		log.debug("rerollMainQuests: 진행중인 퀘스트 제외 후 MainQuest 개수: {}", availableMainQuestDtos.size());
		log.debug("rerollMainQuests: 진행중인 퀘스트 제외 후 MainQuest ID: {}", availableMainQuestDtos.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		// 5. 제외할 퀘스트 ID Set 생성
		Set<Long> excludeMainQuestIds = mainQuestsToExclude.stream().collect(Collectors.toSet());
		log.debug("rerollMainQuests: 제외할 메인 퀘스트 ID (Set): {}", excludeMainQuestIds);

		// 6. 리롤 시 제외할 퀘스트를 필터링
		List<MainQuestResponseDto> filteredForRerollQuests = availableMainQuestDtos.stream()
			.filter(mainQuestDto -> !excludeMainQuestIds.contains(mainQuestDto.id()))
			.collect(Collectors.toList());
		log.debug("rerollMainQuests: 리롤 제외 후 남은 메인 퀘스트 개수: {}", filteredForRerollQuests.size());
		log.debug("rerollMainQuests: 리롤 제외 후 남은 메인 퀘스트 ID: {}", filteredForRerollQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		// 7. 남은 퀘스트가 충분하지 않으면 제외된 퀘스트 중 일부를 재사용
		List<MainQuestResponseDto> finalSelectedMainQuests;
		if (filteredForRerollQuests.size() >= OUTPUT_MAINQUEST_NUM) {
			// 충분하면 리롤 필터링된 퀘스트 중에서만 선택
			finalSelectedMainQuests = questUtil.selectRandoms(filteredForRerollQuests, OUTPUT_MAINQUEST_NUM);
		} else {
			// 부족하면 제외된 퀘스트 중 일부를 재활용하여 OUTPUT_MAINQUEST_NUM 개수 맞추기
			List<MainQuestResponseDto> excludedReusableQuests = availableMainQuestDtos.stream()
				.filter(mainQuestDto -> excludeMainQuestIds.contains(mainQuestDto.id()))
				.collect(Collectors.toList());

			// 리롤 후 남은 퀘스트와 재사용 가능한 제외된 퀘스트를 합쳐서 랜덤 선택
			List<MainQuestResponseDto> combinedQuests = Stream.concat(
				filteredForRerollQuests.stream(),
				excludedReusableQuests.stream()
			).collect(Collectors.toList());

			finalSelectedMainQuests = questUtil.selectRandoms(combinedQuests, OUTPUT_MAINQUEST_NUM);
		}

		log.info("rerollMainQuests 메서드 완료. 최종 선택된 메인 퀘스트 개수: {}", finalSelectedMainQuests.size());
		log.info("최종 선택된 메인 퀘스트 ID: {}", finalSelectedMainQuests.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		return finalSelectedMainQuests;
	}

	/**
	 * 공통 로직: 선택된 능력치 및 테마를 기준으로 DB에서 퀘스트를 조회하고,
	 * 사용자 진행 중인 퀘스트를 제외한 후 DTO로 변환하여 반환합니다.
	 */
	private List<MainQuestResponseDto> getAvailableMainQuestDtos(List<Integer> attributes, Long userId, Long themeId) {
		// 1. 선택된 Attribute ID 목록을 비트마스크로 조합
		int selectedAttributesBitmask = questUtil.calculateCombinedBitmask(attributes);
		log.debug("계산된 selectedAttributesBitmask: {}", selectedAttributesBitmask);

		// 2. DB에서 메인 퀘스트 필터링 및 조회
		List<MainQuest> allCandidateMainQuests =
			mainQuestRepository.findAllByThemeIdAndAttributes(themeId, selectedAttributesBitmask);
		log.debug("DB 조회 결과 (allCandidateMainQuests) 개수: {}", allCandidateMainQuests.size());
		log.debug("DB 조회 결과 (allCandidateMainQuests) ID: {}", allCandidateMainQuests.stream().map(MainQuest::getId).collect(Collectors.toList()));

		// 3. 진행중인 퀘스트 제외
		Set<MainQuest> userMainQuestSet = usersMainQuestService.getUsersMainQuestByUserId(userId)
			.stream()
			.map(UsersMainQuest::getMainQuest)
			.collect(Collectors.toSet());

		List<MainQuest> availableMainQuests = allCandidateMainQuests.stream()
			.filter(mainQuest -> !userMainQuestSet.contains(mainQuest))
			.toList();

		// 4. MainQuest 엔티티를 MainQuestResponseDto로 변환
		List<MainQuestResponseDto> mainQuestDtos = availableMainQuests.stream()
			.map(mainQuest -> new MainQuestResponseDto(mainQuest.getId(), mainQuest.getName()))
			.collect(Collectors.toList());
		log.debug("MainQuestResponseDto 변환 완료. 변환된 메인 퀘스트 개수: {}", mainQuestDtos.size());
		log.debug("변환된 메인 퀘스트 ID: {}", mainQuestDtos.stream().map(MainQuestResponseDto::id).collect(Collectors.toList()));

		return mainQuestDtos;
	}
}