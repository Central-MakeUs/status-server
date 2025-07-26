package com.statoverflow.status.domain.quest.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statoverflow.status.domain.quest.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuest;
import com.statoverflow.status.domain.quest.enums.FrequencyType;
import com.statoverflow.status.domain.quest.enums.QuestStatus;
import com.statoverflow.status.domain.quest.repository.MainQuestRepository;
import com.statoverflow.status.domain.quest.repository.MainSubQuestRepository;
import com.statoverflow.status.domain.quest.repository.UsersMainQuestRepository;
import com.statoverflow.status.domain.quest.repository.UsersSubQuestLogRepository;
import com.statoverflow.status.domain.quest.repository.UsersSubQuestRepository;
import com.statoverflow.status.domain.quest.service.interfaces.UsersSubQuestService;
import com.statoverflow.status.domain.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersSubQuestServiceImpl implements UsersSubQuestService {

	private final UsersMainQuestRepository usersMainQuestRepository;
	private final UsersSubQuestRepository usersSubQuestRepository;
	private final UsersSubQuestLogRepository usersSubQuestLogRepository;


	@Override
	public List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long id) {

		// 오늘 완료 가능한 서브 퀘스트 리스트 가져오기
		List<UsersSubQuest> questList = usersSubQuestRepository.findByUsersIdAndStatus(id, QuestStatus.ACTIVE);

		// 2. 각 서브 퀘스트가 오늘 수행되어야 하는지 판단하고 DTO로 매핑
		return questList.stream()
			.map(this::mapToUsersSubQuestResponseDto) // DTO로 매핑
			.collect(Collectors.toList());
	}

	@Override
	public List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long userId, Long mainQuestId) {
		List<UsersSubQuest> questList = usersSubQuestRepository.findByUsersIdAndMainQuestIdAndStatus(userId, mainQuestId,
			QuestStatus.ACTIVE);

		// 2. 각 서브 퀘스트가 오늘 수행되어야 하는지 판단하고 DTO로 매핑
		return questList.stream()
			.map(this::mapToUsersSubQuestResponseDto) // DTO로 매핑
			.collect(Collectors.toList());
	}

	private SubQuestResponseDto.UsersSubQuestResponseDto mapToUsersSubQuestResponseDto(UsersSubQuest usersSubQuest) {

		SubQuestResponseDto subQuestInfo = new SubQuestResponseDto(
			usersSubQuest.getId(),
			usersSubQuest.getFrequencyType(),
			usersSubQuest.getActionUnitType().getUnit(),
			usersSubQuest.getActionUnitNum(),
			AttributeDto.fromUsersEntity(usersSubQuest),
			usersSubQuest.getDescription()
		);

		RepeatAndEssential rae = repeatCntAndEssential(usersSubQuest);

		int repeatCnt = rae.repeatCnt;
		boolean essential = rae.essential;

		return new SubQuestResponseDto.UsersSubQuestResponseDto(
			subQuestInfo,
			repeatCnt,
			essential
		);
	}

	private record RepeatAndEssential(int repeatCnt, boolean essential) {}

	private RepeatAndEssential repeatCntAndEssential(UsersSubQuest usersSubQuest) {

		LocalDate today = LocalDate.now();
		FrequencyType frequencyType = usersSubQuest.getFrequencyType();

		log.info("--- 서브 퀘스트 ID: {}, 타입: {} 에 대한 RepeatAndEssential 계산 시작 ---", usersSubQuest.getId(), frequencyType);
		log.info("오늘 날짜: {}", today);


		switch (usersSubQuest.getFrequencyType()) {
			case DAILY:
				log.info("일간 퀘스트 - 로그 조회 시작 날짜: {}", today);
				int logCnt = usersSubQuestLogRepository.countByUsersSubQuestIdAndCreatedAtAfter(usersSubQuest.getId(),
					today);
				log.info("일간 퀘스트 - 오늘 완료된 로그 수: {}", logCnt);

				return new RepeatAndEssential(FrequencyType.DAILY.getPer() - logCnt, logCnt == 0);

			case WEEKLY_1:
			case WEEKLY_2:
			case WEEKLY_3:
			case WEEKLY_4:
			case WEEKLY_5:
			case WEEKLY_6:

				// 1. 현재 주차의 시작일을 구합니다.
				LocalDate mainQuestStartDate = usersSubQuest.getMainQuest().getStartDate();
				long daysSinceMainQuestStart = ChronoUnit.DAYS.between(mainQuestStartDate, today);
				long currentWeekOffset = (daysSinceMainQuestStart / 7) * 7;
				LocalDate currentWeekStartDate = mainQuestStartDate.plusDays(currentWeekOffset);

				log.info("주간 퀘스트 - 메인 퀘스트 시작일: {}", mainQuestStartDate);
				log.info("주간 퀘스트 - 메인 퀘스트 시작일로부터 오늘까지의 총 일수: {}", daysSinceMainQuestStart);
				log.info("주간 퀘스트 - 현재 주차 시작일 (계산): {}", currentWeekStartDate);


				// 2. 현재 주차의 마지막 날 (7일차)을 구합니다.
				LocalDate currentWeekEndDate = currentWeekStartDate.plusDays(6); // 7일 주기이므로 시작일 + 6일
				log.info("주간 퀘스트 - 현재 주차 종료일 (계산): {}", currentWeekEndDate);

				// 3. 오늘 날짜가 현재 주차의 마지막 날부터 며칠 떨어져 있는지 계산합니다.
				//    (여기서 '1'은 마지막 날짜 자신을 포함하는 의미입니다.)
				long daysFromEndOfCurrentWeek = ChronoUnit.DAYS.between(today, currentWeekEndDate) + 1;
				log.info("주간 퀘스트 - 현재 주차 종료일까지 남은 일수 (종료일 포함): {}", daysFromEndOfCurrentWeek);

				log.info("주간 퀘스트 - 로그 조회 시작 날짜: {}", currentWeekStartDate);

				logCnt = usersSubQuestLogRepository.countByUsersSubQuestIdAndCreatedAtAfter(usersSubQuest.getId(),
					currentWeekStartDate);
				log.info("주간 퀘스트 - 현재 주차 내 완료된 로그 수: {}", logCnt);

				int cnt = (usersSubQuest.getFrequencyType().getCnt() - logCnt);

				return new RepeatAndEssential(cnt, daysFromEndOfCurrentWeek - cnt <= 0);

			case MONTHLY_1:
			case MONTHLY_2:
			case MONTHLY_3:
			case MONTHLY_4:

				logCnt = usersSubQuestLogRepository.countByUsersSubQuestIdAndCreatedAtAfter(usersSubQuest.getId(),
					usersSubQuest.getMainQuest()
						.getStartDate());
				log.info("월간 퀘스트 - 현재 월 내 완료된 로그 수: {}", logCnt);


				LocalDate endDate = usersSubQuest.getMainQuest().getEndDate();
				Long daysFromEndDate = ChronoUnit.DAYS.between(endDate, today) + 1;


				cnt = (usersSubQuest.getFrequencyType().getCnt() - logCnt);

				return new RepeatAndEssential(cnt, daysFromEndDate - cnt <= 0);

			default:
				return new RepeatAndEssential(0, false);
		}
	}
}
