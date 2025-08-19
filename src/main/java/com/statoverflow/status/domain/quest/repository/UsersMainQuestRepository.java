package com.statoverflow.status.domain.quest.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.master.entity.MainQuest;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.enums.QuestStatus;

@Repository
public interface UsersMainQuestRepository extends JpaRepository<UsersMainQuest, Long> {

	List<UsersMainQuest> findByStatusAndEndDateBefore(QuestStatus status, LocalDate endDate);

	Optional<UsersMainQuest> findByIdAndStatusNotIn(Long mainQuestId, List<QuestStatus> status);

	List<UsersMainQuest> findByUsersIdAndStatusIn(Long userId, List<QuestStatus> list, Sort sort);
}
