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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class QuestScheduler {

	private final UsersMainQuestRepository usersMainQuestRepository;
	private final UsersSubQuestRepository usersSubQuestRepository;

	// todo 3: ACCOMPLISHED를 전부 ACTIVE 처리
	@Scheduled(cron = "0 1 0 * * *", zone = "Asia/Seoul")
	public void dailySubQuestMaintenance() {
		log.info("### dailySubQuestMaintenance 스케줄러 시작 (매일 00시 01분)");
		List<UsersSubQuest> subQuests = usersSubQuestRepository.findByStatus(QuestStatus.ACCOMPLISHED);

		if (!subQuests.isEmpty()) {
			log.info("ACCOMPLISHED 상태인 서브 퀘스트 {}개를 ACTIVE로 변경합니다.", subQuests.size());
			subQuests.forEach(subQuest -> subQuest.setStatus(QuestStatus.ACTIVE));
			log.info("ACCOMPLISHED 서브 퀘스트 변경 완료.");
		} else {
			log.info("ACCOMPLISHED 상태인 서브 퀘스트가 없습니다.");
		}
		log.info("### dailySubQuestMaintenance 스케줄러 종료.");
	}

	// todo 2 : 매일 - 서브퀘스트) 해당 요일의 WEEKLY_COMPLETED 다 ACTIVE 처리
	@Scheduled(cron = "0 2 0 * * *", zone = "Asia/Seoul")
	public void weeklySubQuestMaintenance() {
		log.info("### weeklySubQuestMaintenance 스케줄러 시작 (매일 00시 02분)");
		List<UsersSubQuest> subQuests = usersSubQuestRepository.findByStatus(QuestStatus.WEEKLY_ACCOMPLISHED);

		long changedCount = 0;
		for (UsersSubQuest usersSubQuest : subQuests) {
			if (usersSubQuest.getMainQuest().getStartDate().getDayOfWeek().equals(LocalDate.now().getDayOfWeek())) {
				usersSubQuest.setStatus(QuestStatus.ACTIVE);
				changedCount++;
			}
		}

		log.info("WEEKLY_ACCOMPLISHED 상태인 서브 퀘스트 중 {}개를 ACTIVE로 변경했습니다.", changedCount);
		log.info("### weeklySubQuestMaintenance 스케줄러 종료.");
	}

	// todo 1 : 매일 - 메인퀘스트) 기간 지난 ACTIVE 퀘스트들 FAILED 처리
	@Scheduled(cron = "0 5 0 * * *", zone = "Asia/Seoul")
	public void invalidateExpiredMainQuests() {
		log.info("### invalidateExpiredMainQuests 스케줄러 시작 (매일 00시 05분)");
		List<UsersMainQuest> mainQuests = usersMainQuestRepository.findByStatusAndEndDateBefore(QuestStatus.ACTIVE,
			LocalDate.now());

		if (!mainQuests.isEmpty()) {
			log.info("만료된 메인 퀘스트 {}개를 FAILED 상태로 변경합니다.", mainQuests.size());
			mainQuests.forEach(mainQuest -> mainQuest.setStatus(QuestStatus.FAILED));
			log.info("만료된 메인 퀘스트 변경 완료.");
		} else {
			log.info("만료된 ACTIVE 상태의 메인 퀘스트가 없습니다.");
		}
		log.info("### invalidateExpiredMainQuests 스케줄러 종료.");
	}


}
