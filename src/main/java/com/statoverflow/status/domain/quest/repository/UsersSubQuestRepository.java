package com.statoverflow.status.domain.quest.repository;

import com.statoverflow.status.domain.quest.enums.QuestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.quest.entity.UsersSubQuest;

import java.util.List;

@Repository
public interface UsersSubQuestRepository extends JpaRepository<UsersSubQuest, Long> {
    List<UsersSubQuest> findByUsersIdAndStatus(Long userId, QuestStatus status);

}
