package com.statoverflow.status.domain.quest.repository;

import com.statoverflow.status.domain.quest.enums.QuestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.quest.entity.UsersSubQuest;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersSubQuestRepository extends JpaRepository<UsersSubQuest, Long> {
    List<UsersSubQuest> findByUsersIdAndStatus(Long userId, QuestStatus status);

    List<UsersSubQuest> findByUsersIdAndMainQuestIdAndStatus(Long userId, Long mainQuestId, QuestStatus questStatus);

    List<UsersSubQuest> findByUsersIdAndMainQuestIdAndStatusIn(Long userId, Long mainQuestId, List<QuestStatus> statuses);

    Optional<UsersSubQuest> findByIdAndUsersIdAndStatus(Long id, Long userId, QuestStatus questStatus);

	List<UsersSubQuest> findByStatus(QuestStatus questStatus);
}
