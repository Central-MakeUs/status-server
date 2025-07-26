package com.statoverflow.status.domain.quest.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statoverflow.status.domain.quest.entity.UsersSubQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuestLog;

public interface UsersSubQuestLogRepository extends JpaRepository<UsersSubQuestLog, Long> {

	int countByUsersSubQuestIdAndCreatedAtAfter(Long usersSubQuestId, LocalDateTime startDate);

	List<UsersSubQuestLog> findByUsersSubQuest(UsersSubQuest subQuest);

	List<UsersSubQuestLog> findByUsersSubQuestId(Long id);
}
