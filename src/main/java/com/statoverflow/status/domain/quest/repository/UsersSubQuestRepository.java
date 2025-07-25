package com.statoverflow.status.domain.quest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.quest.entity.UsersSubQuest;

@Repository
public interface UsersSubQuestRepository extends JpaRepository<UsersSubQuest, Long> {
}
