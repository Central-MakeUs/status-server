package com.statoverflow.status.domain.quest.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.master.entity.MainSubQuest;
import com.statoverflow.status.domain.master.entity.SubQuest;
import com.statoverflow.status.domain.quest.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.CreateQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuest;
import com.statoverflow.status.domain.quest.repository.MainQuestRepository;
import com.statoverflow.status.domain.quest.repository.MainSubQuestRepository;
import com.statoverflow.status.domain.quest.repository.SubQuestRepository;
import com.statoverflow.status.domain.quest.repository.UsersMainQuestRepository;
import com.statoverflow.status.domain.quest.repository.UsersSubQuestRepository;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserQuestServiceImpl implements UserQuestService {

	private final MainQuestRepository mainQuestRepository;
	private final UsersRepository usersRepository;
	private final UsersMainQuestRepository usersMainQuestRepository;
	private final UsersSubQuestRepository usersSubQuestRepository;
	private final SubQuestRepository subQuestRepository;
	private final AttributeRepository attributeRepository;
	private final MainSubQuestRepository mainSubQuestRepository;

	@Override
	public CreateQuestResponseDto create(CreateQuestRequestDto dto, Long userId) {

		MainQuest mainQuest = mainQuestRepository.findById(dto.mainQuest()).orElseThrow();
		Users user = usersRepository.findById(userId).orElseThrow();

		// 1. user main quest 내 등록
		UsersMainQuest umq = UsersMainQuest.builder()
			.mainQuest(mainQuest)
			.user(user)
			.startDate(dto.startDate().atStartOfDay())
			.endDate(dto.endDate().atStartOfDay())
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
					.attribute1(mainSubQuest.getAttribute1())
					.exp1(mainSubQuest.getExp1());

				if (mainSubQuest.getAttribute2() != null) {
					usersSubQuestBuilder.attribute2(mainSubQuest.getAttribute2());
					usersSubQuestBuilder.exp2(mainSubQuest.getExp2());
				}


				UsersSubQuest usersSubQuest = usersSubQuestBuilder.build();
				UsersSubQuest savedUsersSubQuest = usersSubQuestRepository.save(usersSubQuest);
				createdUsersSubQuests.add(savedUsersSubQuest);
			});


		List<AttributeDto> mainQuestAttributes = AttributeDto.fromMainEntity(mainQuest);


		// SubQuestResponseDto 리스트 생성
		List<SubQuestResponseDto> subQuestResponseDtos = createdUsersSubQuests.stream()
			.map(usq -> {
				List<AttributeDto> subQuestAttributes = AttributeDto.fromUsersEntity(usq);

				return new SubQuestResponseDto(
					usq.getId(),
					usq.getFrequencyType().getDescription(),
					usq.getActionUnitType().getUnit(),
					usq.getActionUnitNum(),
					subQuestAttributes,
					usq.getDescription()
				);
			})
			.collect(Collectors.toList());

		long totalDaysBetween = ChronoUnit.DAYS.between(dto.startDate(), dto.endDate());
		int totalWeeks = (int) Math.ceil((double) totalDaysBetween / 7.0);

		return new CreateQuestResponseDto(
			umq.getId(),
			umq.getStartDate().toLocalDate(),
			umq.getEndDate().toLocalDate(),
			totalWeeks,
			umq.getTitle(),
			mainQuestAttributes,
			subQuestResponseDtos
		);
	}
}
