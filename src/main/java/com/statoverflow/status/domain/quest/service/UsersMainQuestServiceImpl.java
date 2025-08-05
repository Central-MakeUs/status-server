package com.statoverflow.status.domain.quest.service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

	@Override
	public CreateQuestResponseDto create(CreateQuestRequestDto dto, Long userId) {

		MainQuest mainQuest = mainQuestRepository.findById(dto.mainQuest()).orElseThrow();
		Users user = usersRepository.findById(userId).orElseThrow();

		// 1. user main quest 내 등록
		UsersMainQuest umq = UsersMainQuest.builder()
			.mainQuest(mainQuest)
			.users(user)
			.startDate(dto.startDate())
			.endDate(dto.endDate())
			.build();

		usersMainQuestRepository.save(umq);

		// 2. user sub quest 내 등록
		List<UsersSubQuest> createdUsersSubQuests = new ArrayList<>();
		dto.subQuests().stream()
			.forEach(subQuestInfo -> {
				MainSubQuest mainSubQuest = mainSubQuestRepository.findByMainQuestIdAndSubQuestId(mainQuest.getId(), subQuestInfo.id());
				UsersSubQuest.UsersSubQuestBuilder usersSubQuestBuilder = UsersSubQuest.builder()
					.users(user)
					.mainQuest(umq)
					.subQuest(mainSubQuest.getSubQuest())
					.frequencyType(subQuestInfo.frequencyType())
					.actionUnitNum(subQuestInfo.actionUnitNum())
					.attribute1(mainSubQuest.getAttribute1());

				double multiplier = mainSubQuest.getSubQuest().getActionUnitType().getExpMultiplier(subQuestInfo.actionUnitNum()).orElseThrow();


					usersSubQuestBuilder.exp1((int)(mainSubQuest.getExp1()*multiplier));

				if (mainSubQuest.getAttribute2() != null) {
					usersSubQuestBuilder.attribute2(mainSubQuest.getAttribute2());
					usersSubQuestBuilder.exp2((int)(mainSubQuest.getExp2()*multiplier));
				}

				// requiredLog 계산 로직
				int requiredLog;
				long totalDaysBetween = ChronoUnit.DAYS.between(dto.startDate(), dto.endDate());

				switch (subQuestInfo.frequencyType()) {
					case DAILY:
						requiredLog = (int) (totalDaysBetween + 1);
						break;
					case MONTHLY_1:
					case MONTHLY_2:
					case MONTHLY_3:
					case MONTHLY_4:
						requiredLog = subQuestInfo.frequencyType().getCnt();
						break;
					case WEEKLY_1:
					case WEEKLY_2:
					case WEEKLY_3:
					case WEEKLY_4:
					case WEEKLY_5:
					case WEEKLY_6:
						long totalWeeks = (long) Math.ceil((double) (totalDaysBetween + 1) / 7.0);
						requiredLog = (int) (totalWeeks * subQuestInfo.frequencyType().getCnt());
						break;
					default:
						requiredLog = 0; // 또는 예외 처리
						break;
				}
				usersSubQuestBuilder.requiredLog(requiredLog);

				UsersSubQuest usersSubQuest = usersSubQuestBuilder.build();
				UsersSubQuest savedUsersSubQuest = usersSubQuestRepository.save(usersSubQuest);
				createdUsersSubQuests.add(savedUsersSubQuest);
			});


		List<AttributeDto> mainQuestAttributes = AttributeDto.fromMainQuest(mainQuest);


		// SubQuestResponseDto 리스트 생성
		List<SubQuestResponseDto> subQuestResponseDtos = createdUsersSubQuests.stream()
			.map(usq -> {
				List<AttributeDto> subQuestAttributes = AttributeDto.fromUsersSubQuest(usq);
				// 설명 필드 생성 (플레이스홀더 치환)
				String formattedDesc = String.format(usq.getDescription(), usq.getActionUnitNum());

				return new SubQuestResponseDto(
					usq.getId(),
					usq.getFrequencyType(),
					usq.getActionUnitType().getUnit(),
					usq.getActionUnitNum(),
					subQuestAttributes,
					formattedDesc
				);
			})
			.collect(Collectors.toList());

		long totalDaysBetween = ChronoUnit.DAYS.between(dto.startDate(), dto.endDate());
		int totalWeeks = (int) Math.ceil((double) totalDaysBetween / 7.0);

		return new CreateQuestResponseDto(
			umq.getId(),
			umq.getStartDate(),
			umq.getEndDate(),
			totalWeeks,
			umq.getTitle(),
			mainQuestAttributes,
			subQuestResponseDtos,
			mainQuest.getNpcName()
		);
	}


	@Override
	public void deleteMainQuest(Long mainQuestId) {
		UsersMainQuest umq = usersMainQuestRepository.findById(mainQuestId).orElseThrow();
		umq.setStatus(QuestStatus.DELETED);
		List<UsersSubQuest> usq = umq.getUsersSubQuests();
		usq.stream()
			.forEach(subQuestInfo -> {
				subQuestInfo.setStatus(QuestStatus.DELETED);
			});
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsersMainQuestResponseDto> getUsersMainQuests(Long userId) {

		return getUsersMainQuestByUserId(userId).stream()
			.map(this::mapToDto)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsersMainQuest> getUsersMainQuestByUserId(Long userId) {
		return usersMainQuestRepository.findByUsersIdAndStatus(userId, QuestStatus.ACTIVE);
	}

	@Override
	public UsersMainQuestResponseDto getUsersMainQuestById(Long userId, Long mainQuestId) {
		return getUsersMainQuestByUserId(userId).stream()
			.filter(usersMainQuest -> Objects.equals(usersMainQuest.getId(), mainQuestId))
			.findFirst()
			.map(this::mapToDto)
			.orElseThrow(() -> new CustomException(ErrorType.MAINQUEST_NOT_FOUND));
	}

	private UsersMainQuestResponseDto mapToDto(UsersMainQuest umq) {

		int totalRequiredLog = umq.getUsersSubQuests().stream()
			.mapToInt(UsersSubQuest::getRequiredLog)
			.sum();

		int totalCompletedLog = umq.getUsersSubQuests().stream()
			.mapToInt(subQuest -> subQuest.getLogs().size())
			.sum();

		log.info("totalWeeks 계산: {}", (ChronoUnit.DAYS.between(umq.getStartDate(), umq.getEndDate())+1));
		UsersMainQuestResponseDto umqrd = new UsersMainQuestResponseDto(
			umq.getId(),
			umq.getStartDate(),
			umq.getEndDate(),
			(int) (ChronoUnit.DAYS.between(umq.getStartDate(), umq.getEndDate())+1)/7,
			umq.getTitle(),
			AttributeDto.fromUsersMainQuest(umq),
			totalCompletedLog * 100 / totalRequiredLog
		);
		return umqrd;
	}

}
