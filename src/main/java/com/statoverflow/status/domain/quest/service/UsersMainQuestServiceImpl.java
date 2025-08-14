package com.statoverflow.status.domain.quest.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.master.entity.MainSubQuest;
import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.request.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.CreateQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.UsersMainQuestResponseDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuest;
import com.statoverflow.status.domain.quest.enums.FrequencyType;
import com.statoverflow.status.domain.quest.enums.QuestStatus;
import com.statoverflow.status.domain.quest.repository.MainQuestRepository;
import com.statoverflow.status.domain.quest.repository.MainSubQuestRepository;
import com.statoverflow.status.domain.quest.repository.UsersMainQuestRepository;
import com.statoverflow.status.domain.quest.repository.UsersSubQuestRepository;
import com.statoverflow.status.domain.quest.service.interfaces.UsersMainQuestService;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.repository.UsersRepository;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 사용자 메인 퀘스트 관리 서비스
 *
 * 주요 기능:
 * - 퀘스트 생성 및 삭제
 * - 사용자별 퀘스트 조회
 * - 퀘스트 진행률 계산
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UsersMainQuestServiceImpl implements UsersMainQuestService {

	private final MainQuestRepository mainQuestRepository;
	private final UsersRepository usersRepository;
	private final UsersMainQuestRepository usersMainQuestRepository;
	private final UsersSubQuestRepository usersSubQuestRepository;
	private final MainSubQuestRepository mainSubQuestRepository;

	/**
	 * 새로운 퀘스트를 생성합니다.
	 *
	 * @param dto 퀘스트 생성 요청 정보
	 * @param userId 사용자 ID
	 * @return 생성된 퀘스트 정보
	 */
	@Override
	public CreateQuestResponseDto create(CreateQuestRequestDto dto, Long userId) {
		// 1. 기본 엔티티 조회 및 검증
		MainQuest mainQuest = findMainQuestById(dto.mainQuest());
		Users user = findUserById(userId);

		// 2. 메인 퀘스트 생성
		UsersMainQuest usersMainQuest = createUsersMainQuest(mainQuest, user, dto);

		// 3. 서브 퀘스트 생성
		List<UsersSubQuest> createdSubQuests = createUsersSubQuests(dto, mainQuest, user, usersMainQuest);

		// 4. 응답 DTO 생성
		return buildCreateQuestResponse(usersMainQuest, mainQuest, createdSubQuests, dto);
	}

	/**
	 * 퀘스트를 삭제합니다 (논리적 삭제)
	 *
	 * @param mainQuestId 삭제할 메인 퀘스트 ID
	 */
	@Override
	public void deleteMainQuest(Long mainQuestId) {
		UsersMainQuest mainQuest = findActiveUsersMainQuest(mainQuestId);

		// 메인 퀘스트와 연관된 서브 퀘스트들을 모두 삭제 상태로 변경
		mainQuest.setStatus(QuestStatus.DELETED);
		mainQuest.getUsersSubQuests().forEach(subQuest ->
			subQuest.setStatus(QuestStatus.DELETED)
		);

		log.info("퀘스트 삭제 완료 - mainQuestId: {}", mainQuestId);
	}

	/**
	 * 사용자의 모든 활성 퀘스트를 조회합니다.
	 *
	 * @param userId 사용자 ID
	 * @return 사용자의 퀘스트 목록
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UsersMainQuestResponseDto> getUsersMainQuests(Long userId) {
		return getUsersMainQuestByUserId(userId).stream()
			.map(this::convertToResponseDto)
			.collect(Collectors.toList());
	}

	/**
	 * 사용자 ID로 활성 퀘스트 목록을 조회합니다.
	 *
	 * @param userId 사용자 ID
	 * @return 활성 퀘스트 목록
	 */
	@Override
	@Transactional(readOnly = true)
	public List<UsersMainQuest> getUsersMainQuestByUserId(Long userId) {
		return usersMainQuestRepository.findByUsersIdAndStatus(userId, QuestStatus.ACTIVE);
	}

	/**
	 * 특정 사용자의 특정 퀘스트를 조회합니다.
	 *
	 * @param userId 사용자 ID
	 * @param mainQuestId 퀘스트 ID
	 * @return 퀘스트 정보
	 */
	@Override
	public UsersMainQuestResponseDto getUsersMainQuestById(Long userId, Long mainQuestId) {
		return getUsersMainQuestByUserId(userId).stream()
			.filter(quest -> Objects.equals(quest.getId(), mainQuestId))
			.findFirst()
			.map(this::convertToResponseDto)
			.orElseThrow(() -> new CustomException(ErrorType.MAINQUEST_NOT_FOUND));
	}

	// ==================== Private Helper Methods ====================

	/**
	 * MainQuest ID로 엔티티를 조회합니다.
	 */
	private MainQuest findMainQuestById(Long mainQuestId) {
		return mainQuestRepository.findById(mainQuestId)
			.orElseThrow(() -> new CustomException(ErrorType.MAINQUEST_NOT_FOUND));
	}

	/**
	 * User ID로 엔티티를 조회합니다.
	 */
	private Users findUserById(Long userId) {
		return usersRepository.findById(userId)
			.orElseThrow(() -> new CustomException(ErrorType.USER_NOT_FOUND));
	}

	/**
	 * 활성 상태의 UsersMainQuest를 조회합니다.
	 */
	private UsersMainQuest findActiveUsersMainQuest(Long mainQuestId) {
		return usersMainQuestRepository.findByIdAndStatusNotIn(
			mainQuestId,
			Arrays.asList(QuestStatus.DELETED, QuestStatus.COMPLETED)
		).orElseThrow(() -> new CustomException(ErrorType.INVALID_MAINQUEST));
	}

	/**
	 * UsersMainQuest 엔티티를 생성합니다.
	 */
	private UsersMainQuest createUsersMainQuest(MainQuest mainQuest, Users user, CreateQuestRequestDto dto) {
		UsersMainQuest usersMainQuest = UsersMainQuest.builder()
			.mainQuest(mainQuest)
			.users(user)
			.startDate(dto.startDate())
			.endDate(dto.endDate())
			.build();

		return usersMainQuestRepository.save(usersMainQuest);
	}

	/**
	 * 모든 서브 퀘스트들을 생성합니다.
	 */
	private List<UsersSubQuest> createUsersSubQuests(CreateQuestRequestDto dto, MainQuest mainQuest,
		Users user, UsersMainQuest usersMainQuest) {
		return dto.subQuests().stream()
			.map(subQuestInfo -> createSingleSubQuest(subQuestInfo, dto, mainQuest, user, usersMainQuest))
			.collect(Collectors.toList());
	}

	/**
	 * 개별 서브 퀘스트를 생성합니다.
	 */
	private UsersSubQuest createSingleSubQuest(CreateQuestRequestDto.SubQuestInfo subQuestInfo,
		CreateQuestRequestDto dto, MainQuest mainQuest,
		Users user, UsersMainQuest usersMainQuest) {

		MainSubQuest mainSubQuest = findMainSubQuest(mainQuest.getId(), subQuestInfo.id());
		double expMultiplier = calculateExperienceMultiplier(mainSubQuest, subQuestInfo.actionUnitNum());
		int requiredLog = calculateRequiredLog(subQuestInfo.frequencyType(), dto.startDate(), dto.endDate());

		UsersSubQuest.UsersSubQuestBuilder builder = UsersSubQuest.builder()
			.users(user)
			.mainQuest(usersMainQuest)
			.description(formatDescription(mainSubQuest.getSubQuest().getName(), subQuestInfo.actionUnitNum()))
			.subQuest(mainSubQuest.getSubQuest())
			.frequencyType(subQuestInfo.frequencyType())
			.actionUnitNum(subQuestInfo.actionUnitNum())
			.attribute1(mainSubQuest.getAttribute1())
			.exp1(calculateExperience(mainSubQuest.getExp1(), expMultiplier))
			.requiredLog(requiredLog);

		// 두 번째 속성이 있는 경우에만 설정
		if (mainSubQuest.getAttribute2() != null) {
			builder.attribute2(mainSubQuest.getAttribute2())
				.exp2(calculateExperience(mainSubQuest.getExp2(), expMultiplier));
		}

		return usersSubQuestRepository.save(builder.build());
	}

	/**
	 * MainSubQuest를 조회합니다.
	 */
	private MainSubQuest findMainSubQuest(Long mainQuestId, Long subQuestId) {
		MainSubQuest mainSubQuest = mainSubQuestRepository.findByMainQuestIdAndSubQuestId(mainQuestId, subQuestId);
		if (mainSubQuest == null) {
			throw new CustomException(ErrorType.SUBQUEST_NOT_FOUND);
		}
		return mainSubQuest;
	}

	/**
	 * 경험치 배율을 계산합니다.
	 */
	private double calculateExperienceMultiplier(MainSubQuest mainSubQuest, int actionUnitNum) {
		return mainSubQuest.getSubQuest()
			.getActionUnitType()
			.getExpMultiplier(actionUnitNum)
			.orElseThrow(() -> new CustomException(ErrorType.INVALID_FIELD));
	}

	/**
	 * 필요한 로그 수를 빈도 타입에 따라 계산합니다.
	 */
	private int calculateRequiredLog(FrequencyType frequencyType, LocalDate startDate, LocalDate endDate) {
		long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;

		switch (frequencyType) {
			case DAILY:
				return (int) totalDays;

			case MONTHLY_1:
			case MONTHLY_2:
			case MONTHLY_3:
			case MONTHLY_4:
				return frequencyType.getCnt();

			case WEEKLY_1:
			case WEEKLY_2:
			case WEEKLY_3:
			case WEEKLY_4:
			case WEEKLY_5:
			case WEEKLY_6:
				long totalWeeks = (long) Math.ceil((double) totalDays / 7.0);
				return (int) (totalWeeks * frequencyType.getCnt());

			default:
				log.warn("알 수 없는 빈도 타입: {}", frequencyType);
				return 0;
		}
	}

	/**
	 * 설명 문자열의 플레이스홀더를 실제 값으로 치환합니다.
	 */
	private String formatDescription(String description, int actionUnitNum) {
		return description.replace("{actionUnitNum}", String.valueOf(actionUnitNum));
	}

	/**
	 * 경험치를 계산합니다.
	 */
	private int calculateExperience(int baseExp, double multiplier) {
		return (int) (baseExp * multiplier);
	}

	/**
	 * 총 주차 수를 계산합니다.
	 */
	private int calculateTotalWeeks(LocalDate startDate, LocalDate endDate) {
		long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
		return (int) Math.ceil((double) totalDays / 7.0);
	}

	/**
	 * CreateQuestResponseDto를 생성합니다.
	 */
	private CreateQuestResponseDto buildCreateQuestResponse(UsersMainQuest usersMainQuest,
		MainQuest mainQuest,
		List<UsersSubQuest> subQuests,
		CreateQuestRequestDto dto) {

		List<AttributeDto> mainQuestAttributes = AttributeDto.fromMainQuest(mainQuest);
		List<SubQuestResponseDto> subQuestDtos = convertToSubQuestDtos(subQuests);
		int totalWeeks = calculateTotalWeeks(dto.startDate(), dto.endDate());

		return new CreateQuestResponseDto(
			usersMainQuest.getId(),
			usersMainQuest.getStartDate(),
			usersMainQuest.getEndDate(),
			totalWeeks,
			usersMainQuest.getTitle(),
			mainQuestAttributes,
			subQuestDtos,
			mainQuest.getNpcName()
		);
	}

	/**
	 * UsersSubQuest 목록을 SubQuestResponseDto 목록으로 변환합니다.
	 */
	private List<SubQuestResponseDto> convertToSubQuestDtos(List<UsersSubQuest> subQuests) {
		return subQuests.stream()
			.map(this::convertToSubQuestDto)
			.collect(Collectors.toList());
	}

	/**
	 * UsersSubQuest를 SubQuestResponseDto로 변환합니다.
	 */
	private SubQuestResponseDto convertToSubQuestDto(UsersSubQuest subQuest) {
		List<AttributeDto> attributes = AttributeDto.fromUsersSubQuest(subQuest);
		String formattedDescription = String.format(subQuest.getDescription(), subQuest.getActionUnitNum());

		return new SubQuestResponseDto(
			subQuest.getId(),
			subQuest.getFrequencyType(),
			subQuest.getActionUnitType().getUnit(),
			subQuest.getActionUnitNum(),
			attributes,
			formattedDescription
		);
	}

	/**
	 * UsersMainQuest를 UsersMainQuestResponseDto로 변환합니다.
	 */
	private UsersMainQuestResponseDto convertToResponseDto(UsersMainQuest usersMainQuest) {
		QuestProgressInfo progressInfo = calculateQuestProgress(usersMainQuest);
		int totalDays = (int) (ChronoUnit.DAYS.between(
			usersMainQuest.getStartDate(),
			usersMainQuest.getEndDate()) + 1);
		int totalWeeks = totalDays / 7;

		log.debug("퀘스트 진행률 계산 완료 - questId: {}, progress: {}%",
			usersMainQuest.getId(), progressInfo.getProgressPercentage());

		return new UsersMainQuestResponseDto(
			usersMainQuest.getId(),
			usersMainQuest.getStartDate(),
			usersMainQuest.getEndDate(),
			totalWeeks,
			usersMainQuest.getTitle(),
			AttributeDto.fromUsersMainQuest(usersMainQuest),
			progressInfo.getProgressPercentage()
		);
	}

	/**
	 * 퀘스트 진행률 정보를 계산합니다.
	 */
	private QuestProgressInfo calculateQuestProgress(UsersMainQuest usersMainQuest) {
		List<UsersSubQuest> subQuests = usersMainQuest.getUsersSubQuests();

		int totalRequired = subQuests.stream()
			.mapToInt(UsersSubQuest::getRequiredLog)
			.sum();

		int totalCompleted = subQuests.stream()
			.mapToInt(subQuest -> subQuest.getLogs().size())
			.sum();

		int progressPercentage = totalRequired > 0 ? (totalCompleted * 100 / totalRequired) : 0;

		return new QuestProgressInfo(totalRequired, totalCompleted, progressPercentage);
	}

	/**
	 * 퀘스트 진행률 정보를 담는 내부 클래스
	 */
	private static class QuestProgressInfo {
		private final int totalRequired;
		private final int totalCompleted;
		private final int progressPercentage;

		public QuestProgressInfo(int totalRequired, int totalCompleted, int progressPercentage) {
			this.totalRequired = totalRequired;
			this.totalCompleted = totalCompleted;
			this.progressPercentage = progressPercentage;
		}

		public int getTotalRequired() { return totalRequired; }
		public int getTotalCompleted() { return totalCompleted; }
		public int getProgressPercentage() { return progressPercentage; }
	}
}