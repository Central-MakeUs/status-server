package com.statoverflow.status.domain.quest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.enums.QuestStatus;

@Repository
public interface UsersMainQuestRepository extends JpaRepository<UsersMainQuest, Long> {
	List<UsersMainQuest> findByUsersIdAndStatus(Long userId, QuestStatus questStatus);
}
