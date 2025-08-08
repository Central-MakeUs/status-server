package com.statoverflow.status.domain.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuest;
import com.statoverflow.status.domain.quest.enums.QuestStatus;
import com.statoverflow.status.domain.quest.repository.UsersMainQuestRepository;
import com.statoverflow.status.domain.quest.repository.UsersSubQuestRepository;

import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class QuestScheduler {

	private final UsersMainQuestRepository usersMainQuestRepository;
	private final UsersSubQuestRepository usersSubQuestRepository;

	// todo 3: ACCOMPLISHED를 전부 ACTIVE 처리
	@Scheduled(cron = "0 1 0 * * *", zone = "Asia/Seoul")
	private void dailySubQuestMaintenance() {
		List<UsersSubQuest> subQuests = usersSubQuestRepository.findByStatus(QuestStatus.ACCOMPLISHED);
		subQuests.forEach(subQuest -> subQuest.setStatus(QuestStatus.ACTIVE));
	}

	// todo 2 : 매일 - 서브퀘스트) 해당 요일의 WEEKLY_COMPLETED 다 ACTIVE 처리
	@Scheduled(cron = "0 1 0 * * *", zone = "Asia/Seoul")
	private void weeklySubQuestMaintenance() {
		List<UsersSubQuest> subQuests = usersSubQuestRepository.findByStatus(QuestStatus.WEEKLY_ACCOMPLISHED);
		subQuests.stream()
			.filter(usersSubQuest -> usersSubQuest.getMainQuest().getStartDate().getDayOfWeek()
				.equals(LocalDate.now().getDayOfWeek()))
			.forEach(usersSubQuest -> usersSubQuest.setStatus(QuestStatus.ACTIVE));
	}

	// todo 1 : 매일 - 메인퀘스트) 기간 지난 ACTIVE 퀘스트들 FAILED 처리
	@Scheduled(cron = "0 1 0 * * *", zone = "Asia/Seoul")
	private void invalidateExpiredMainQuests() {
		List<UsersMainQuest> mainQuests = usersMainQuestRepository.findByStatusAndEndDateBefore(QuestStatus.ACTIVE,
			LocalDate.now());

		mainQuests.forEach(mainQuest -> mainQuest.setStatus(QuestStatus.FAILED));
	}


}
