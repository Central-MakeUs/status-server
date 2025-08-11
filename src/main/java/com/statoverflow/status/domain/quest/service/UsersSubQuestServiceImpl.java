package com.statoverflow.status.domain.quest.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statoverflow.status.domain.attribute.service.AttributeService;
import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.SubQuestLogDto;
import com.statoverflow.status.domain.quest.dto.response.QuestHistoryByDateDto;
import com.statoverflow.status.domain.quest.dto.response.RewardResponseDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuestLog;
import com.statoverflow.status.domain.quest.enums.FrequencyType;
import com.statoverflow.status.domain.quest.enums.QuestStatus;
import com.statoverflow.status.domain.quest.repository.UsersSubQuestLogRepository;
import com.statoverflow.status.domain.quest.repository.UsersSubQuestRepository;
import com.statoverflow.status.domain.quest.service.interfaces.UsersSubQuestService;
import com.statoverflow.status.domain.users.enums.SourceType;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UsersSubQuestServiceImpl implements UsersSubQuestService {

	private final UsersSubQuestRepository usersSubQuestRepository;
	private final UsersSubQuestLogRepository usersSubQuestLogRepository;
	private final AttributeService attributeService;


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

	@Override
	public List<QuestHistoryByDateDto> getSubQuestsLogs(Long userId, Long mainQuestId) {

		// todo: mainQuestId에 대한 검증 작업 (예: usersMainQuestRepository를 통해 해당 mainQuestId가 userId에 속하는지 확인)

		// 1. 메인 퀘스트 내 활성/주간 완료 상태의 서브 퀘스트 리스트를 가져옵니다.
		List<UsersSubQuest> subQuestList = usersSubQuestRepository.findByUsersIdAndMainQuestIdAndStatusIn(
			userId, mainQuestId, Arrays.asList(QuestStatus.ACTIVE, QuestStatus.ACCOMPLISHED, QuestStatus.WEEKLY_ACCOMPLISHED, QuestStatus.COMPLETED));

		log.debug("조회된 subQuestList: {}", subQuestList);
		// 2. 각 서브 퀘스트에 대한 모든 로그를 가져옵니다.
		// 성능 최적화를 위해, 특정 기간 내의 로그만 가져오거나,
		// subQuestList의 ID를 모아서 UsersSubQuestLogRepository에서 IN 쿼리로 한 번에 가져오는 것을 고려해볼 수 있습니다.
		// List<Long> subQuestIds = subQuestList.stream().map(UsersSubQuest::getId).collect(Collectors.toList());
		// List<UsersSubQuestLog> allLogs = usersSubQuestLogRepository.findByUsersSubQuestIdIn(subQuestIds);
		List<UsersSubQuestLog> allLogs = new ArrayList<>();
		subQuestList.forEach(subQuest -> {
			// 이 findByUsersSubQuest 메서드가 UsersSubQuestLogRepository에 정의되어 있어야 합니다.
			// 또는 findByUsersSubQuestId(subQuest.getId()) 사용.
			List<UsersSubQuestLog> logs = usersSubQuestLogRepository.findByUsersSubQuestId(subQuest.getId());
			log.debug("logs: {}, logs.size(): {}", logs, logs.size());
			allLogs.addAll(logs);
		});

		log.debug("allLogs: {}, allLogs.size(): {}", allLogs, allLogs.size());

		// 3. logsList를 로그 기록 날짜(LocalDate)별로 그룹화합니다.
		Map<LocalDate, List<UsersSubQuestLog>> groupedLogs = allLogs.stream()
			.collect(Collectors.groupingBy(log -> log.getCreatedAt().toLocalDate()));


		// 4. 그룹화된 로그를 QuestHistoryByDateDto 리스트로 변환합니다.
		List<QuestHistoryByDateDto> result = groupedLogs.entrySet().stream()
			.map(entry -> {
				LocalDate logDate = entry.getKey(); // 로그가 기록된 날짜
				List<UsersSubQuestLog> dailyLogs = entry.getValue(); // 해당 날짜의 모든 로그 엔티티

				// 해당 날짜의 각 UsersSubQuestLog를 SubQuestLogsResponseDto로 변환합니다.
				List<QuestHistoryByDateDto.SubQuestLogsResponseDto> dailyHistoryLogs = dailyLogs.stream()
					.map(logEntry -> {
						// UsersSubQuestLog 엔티티에서 필요한 정보 추출
						UsersSubQuest usersSubQuest = logEntry.getUsersSubQuest(); // UsersSubQuestLog에 UsersSubQuest 참조가 있어야 함

						// 2. log 부분 (SubQuestLog 내부 레코드) 생성
						// UsersSubQuestLog 엔티티에서 difficulty, memo, id를 추출
						SubQuestLogDto subQuestLogDto =
							new SubQuestLogDto(
								logEntry.getId(),
								logEntry.getDifficulty(), // UsersSubQuestLog 엔티티에 getDifficulty() 필요
								logEntry.getMemo()       // UsersSubQuestLog 엔티티에 getMemo() 필요
							);

						// 최종 SubQuestLogsResponseDto 생성
						return new QuestHistoryByDateDto.SubQuestLogsResponseDto(mapToUsersSubQuestResponseDto(usersSubQuest), subQuestLogDto);
					})
					.collect(Collectors.toList());

				// QuestHistoryByDateDto 생성
				return new QuestHistoryByDateDto(logDate, dailyHistoryLogs);
			})
			.sorted(Comparator.comparing(QuestHistoryByDateDto::date).reversed()) // 최신 날짜부터 정렬
			.collect(Collectors.toList());

		return result;
	}

	@Override
	public RewardResponseDto doSubQuest(Long userId, SubQuestLogDto dto) {
		log.debug("유저 id: {}, userSubQuestId: {}", userId, dto.id());
		UsersSubQuest usq = usersSubQuestRepository.findByIdAndUsersIdAndStatus(dto.id(), userId, QuestStatus.ACTIVE)
			.orElseThrow(() -> new CustomException(ErrorType.COMPLETED_SUBQUEST));
		log.debug("유저 서브퀘스트 정보: {}", usq.toString());

		UsersSubQuestLog usql = UsersSubQuestLog.builder()
			.usersSubQuest(usq)
			.difficulty(dto.difficulty())
			.memo(dto.memo())
			.build();

		usersSubQuestLogRepository.save(usql);

		attributeService.addExp(usq.getUsers(), AttributeDto.fromUsersSubQuest(usq), SourceType.SUBQUESTLOG);
		// 서브퀘스트 상태 완료 처리
		setStatus(usq);

		// 메인퀘스트 완료 여부 체크
		Boolean isMainQuestCompleted = checkMainQuestCompleted(usq.getMainQuest());

		List<AttributeDto> mainQuestRewards = new ArrayList<>();
		if(isMainQuestCompleted) {
			mainQuestRewards = AttributeDto.fromUsersMainQuest(usq.getMainQuest());
			usq.getMainQuest().setStatus(QuestStatus.ACCOMPLISHED);
			attributeService.addExp(usq.getUsers(), mainQuestRewards, SourceType.MAINQUEST);
		}

		return new RewardResponseDto(AttributeDto.fromUsersSubQuest(usq), mainQuestRewards, isMainQuestCompleted);
	}

	protected boolean checkMainQuestCompleted(UsersMainQuest mainQuest) {
		log.info("메인 퀘스트({} - {}) 완료 여부 확인 시작. 기간: {} ~ {}",
			mainQuest.getId(), mainQuest.getTitle(), mainQuest.getStartDate(), mainQuest.getEndDate());

		boolean allSubQuestsCompleted = true;

		for (UsersSubQuest usersSubQuest : mainQuest.getUsersSubQuests()) {
			boolean subQuestCompleted = false;

			List<UsersSubQuestLog> logs = usersSubQuestLogRepository.findByUsersSubQuestId(usersSubQuest.getId());
			int cnt = logs.size();
			LocalDate startDate = mainQuest.getStartDate();
			LocalDate endDate = mainQuest.getEndDate();

			log.debug("  -> 서브 퀘스트({} - {}) 확인 시작. 타입: {}",
				usersSubQuest.getId(), usersSubQuest.getDescription(), usersSubQuest.getFrequencyType());

			switch (usersSubQuest.getFrequencyType()) {
				case DAILY:
					long requiredDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
					subQuestCompleted = (long) cnt == requiredDays;
					log.debug("    [DAILY] 필수 일수: {}, 실제 로그 수: {}. 완료 여부: {}",
						requiredDays, cnt, subQuestCompleted);

					break;

				case WEEKLY_1:
				case WEEKLY_2:
				case WEEKLY_3:
				case WEEKLY_4:
				case WEEKLY_5:
				case WEEKLY_6:
					if(usersSubQuest.getStatus() != QuestStatus.WEEKLY_ACCOMPLISHED) break;
					int requiredCntPerWeek = usersSubQuest.getFrequencyType().getCnt();
					LocalDate currentWeekStart = startDate;
					subQuestCompleted = true;

					while (!currentWeekStart.isAfter(endDate)) {
						LocalDate currentWeekEnd = currentWeekStart.plusDays(6);
						if (currentWeekEnd.isAfter(endDate)) {
							currentWeekEnd = endDate;
						}

						LocalDate finalCurrentWeekStart = currentWeekStart;
						LocalDate finalCurrentWeekEnd = currentWeekEnd;
						long logsInThisWeek = logs.stream()
							.filter(log -> !log.getCreatedAt().isBefore(finalCurrentWeekStart.atStartOfDay()) && !log.getCreatedAt().isAfter(
								finalCurrentWeekEnd.atStartOfDay()))
							.count();

						if (logsInThisWeek < requiredCntPerWeek) {
							log.debug("    [WEEKLY] 실패! {} ~ {} 기간 동안 필수 로그 수: {}, 실제 로그 수: {}",
								finalCurrentWeekStart, finalCurrentWeekEnd, requiredCntPerWeek, logsInThisWeek);
							subQuestCompleted = false;
							break;
						} else {
							log.debug("    [WEEKLY] 성공. {} ~ {} 기간 동안 필수 로그 수: {}, 실제 로그 수: {}",
								finalCurrentWeekStart, finalCurrentWeekEnd, requiredCntPerWeek, logsInThisWeek);
						}

						currentWeekStart = currentWeekStart.plusWeeks(1);
					}
					break;

				case MONTHLY_1:
				case MONTHLY_2:
				case MONTHLY_3:
				case MONTHLY_4:
					int requiredCntForDuration = usersSubQuest.getFrequencyType().getCnt();
					subQuestCompleted = cnt >= requiredCntForDuration;
					log.debug("    [MONTHLY] 필수 로그 수: {}, 실제 로그 수: {}. 완료 여부: {}",
						requiredCntForDuration, cnt, subQuestCompleted);
					break;
			}

			if (!subQuestCompleted) {
				log.info("  -> 서브 퀘스트({} - {}) 실패. 메인 퀘스트 완료 실패 처리.",
					usersSubQuest.getId(), usersSubQuest.getDescription());
				allSubQuestsCompleted = false;
				break;
			} else {
				log.debug("  -> 서브 퀘스트({} - {}) 완료.",
					usersSubQuest.getId(), usersSubQuest.getDescription());
				usersSubQuest.setStatus(QuestStatus.COMPLETED);
			}
		}

		if (allSubQuestsCompleted) {
			log.debug("✔ 메인 퀘스트({} - {}) 최종 완료.",
				mainQuest.getId(), mainQuest.getTitle());
			return true;
			// todo: 메인 퀘스트 경험치 주기 + status 설정
		} else {
			log.info("❌ 메인 퀘스트({} - {}) 최종 완료 실패.",
				mainQuest.getId(), mainQuest.getTitle());
			return false;
		}
	}

	@Override
	public SubQuestLogDto editSubQuest(Long userId, SubQuestLogDto dto) {
		UsersSubQuestLog usql = usersSubQuestLogRepository.findById(dto.id()).orElseThrow();
		if(dto.difficulty() != null) usql.setDifficulty(dto.difficulty());
		if(dto.memo() != null) usql.setMemo(dto.memo());
		return new SubQuestLogDto(usql.getId(), usql.getDifficulty(), usql.getMemo());
	}

	private SubQuestResponseDto.UsersSubQuestResponseDto mapToUsersSubQuestResponseDto(UsersSubQuest usersSubQuest) {

		SubQuestResponseDto subQuestInfo = mapToSubQuestResponseDto(usersSubQuest);

		RepeatAndEssential rae = repeatCntAndEssential(usersSubQuest);

		int repeatCnt = rae.repeatCnt;
		boolean essential = rae.essential;

		return new SubQuestResponseDto.UsersSubQuestResponseDto(
			usersSubQuest.getMainQuest().getId(),
			subQuestInfo,
			repeatCnt,
			essential
		);
	}

	private SubQuestResponseDto mapToSubQuestResponseDto(UsersSubQuest usersSubQuest) {

		// 설명 필드 생성 (플레이스홀더 치환)
		String formattedDesc = String.format(usersSubQuest.getDescription(), usersSubQuest.getActionUnitNum());
		log.debug("퀘스트 설명 변환: '{}' -> '{}'", usersSubQuest.getDescription(), formattedDesc);

		return new SubQuestResponseDto(
			usersSubQuest.getId(),
			usersSubQuest.getFrequencyType(),
			usersSubQuest.getActionUnitType().getUnit(),
			usersSubQuest.getActionUnitNum(),
			AttributeDto.fromUsersSubQuest(usersSubQuest),
			formattedDesc
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
					today.atStartOfDay());
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
					currentWeekStartDate.atStartOfDay());
				log.info("주간 퀘스트 - 현재 주차 내 완료된 로그 수: {}", logCnt);

				int cnt = (usersSubQuest.getFrequencyType().getCnt() - logCnt);

				return new RepeatAndEssential(cnt, daysFromEndOfCurrentWeek - cnt <= 0);

			case MONTHLY_1:
			case MONTHLY_2:
			case MONTHLY_3:
			case MONTHLY_4:

				logCnt = usersSubQuestLogRepository.countByUsersSubQuestIdAndCreatedAtAfter(usersSubQuest.getId(),
					usersSubQuest.getMainQuest()
						.getStartDate().atStartOfDay());
				log.info("월간 퀘스트 - 현재 월 내 완료된 로그 수: {}", logCnt);


				LocalDate endDate = usersSubQuest.getMainQuest().getEndDate();
				Long daysFromEndDate = ChronoUnit.DAYS.between(endDate, today) + 1;


				cnt = (usersSubQuest.getFrequencyType().getCnt() - logCnt);

				return new RepeatAndEssential(cnt, daysFromEndDate - cnt <= 0);

			default:
				return new RepeatAndEssential(0, false);
		}
	}

	// 서브 퀘스트 로그에 따른 퀘스트 완료 상황
	private void setStatus(UsersSubQuest usq) {
		FrequencyType type = usq.getFrequencyType();
		List<UsersSubQuestLog> logs = usersSubQuestLogRepository.findByUsersSubQuestId(usq.getId());
		switch (type) {
			case WEEKLY_1:
			case WEEKLY_2:
			case WEEKLY_3:
			case WEEKLY_4:
			case WEEKLY_5:
			case WEEKLY_6:
				// 주간 퀘스트 처리
				long requiredCount = type.getCnt();

				// 이번 주 로그만 필터링
				LocalDate mainQuestStartDate = usq.getMainQuest().getStartDate();
				long daysSinceMainQuestStart = ChronoUnit.DAYS.between(mainQuestStartDate, LocalDate.now());
				long currentWeekOffset = (daysSinceMainQuestStart / 7) * 7;
				LocalDate currentWeekStartDate = mainQuestStartDate.plusDays(currentWeekOffset);

				log.info("주간 퀘스트 - 메인 퀘스트 시작일: {}", mainQuestStartDate);
				log.info("주간 퀘스트 - 메인 퀘스트 시작일로부터 오늘까지의 총 일수: {}", daysSinceMainQuestStart);
				log.info("주간 퀘스트 - 현재 주차 시작일 (계산): {}", currentWeekStartDate);

				// 3. 오늘 날짜가 현재 주차의 마지막 날부터 며칠 떨어져 있는지 계산합니다.
				long weeklyLogCount = logs.stream()
					.filter(log -> !log.getCreatedAt().isBefore(currentWeekStartDate.atStartOfDay()))
					.count();

				log.debug("주 내 서브퀘스트 완료 횟수: {}", weeklyLogCount);

				if (weeklyLogCount >= requiredCount) {
					usq.setStatus(QuestStatus.WEEKLY_ACCOMPLISHED);
				} else {
					// 요구 횟수 미만이면 COMPLETED로 처리
					usq.setStatus(QuestStatus.ACCOMPLISHED);
				}
				break;

			default:
				// 월간 퀘스트는 무조건 COMPLETED로 처리
				usq.setStatus(QuestStatus.ACCOMPLISHED);
				break;
		}
	}
}