package com.statoverflow.status.domain.quest.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statoverflow.status.domain.quest.entity.UsersSubQuestLog;

public interface UsersSubQuestLogRepository extends JpaRepository<UsersSubQuestLog, Long> {
	int countByUsersSubQuestIdAndCreatedAtBetween(Long usersSubQuestId, LocalDate startDate, LocalDate endDate);

	int countByUsersSubQuestIdAndCreatedAtAfter(Long usersSubQuestId, LocalDate startDate);

}
