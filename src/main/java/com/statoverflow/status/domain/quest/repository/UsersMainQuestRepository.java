package com.statoverflow.status.domain.quest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.quest.entity.UsersMainQuest;

@Repository
public interface UsersMainQuestRepository extends JpaRepository<UsersMainQuest, Long> {
}
