package com.statoverflow.status.domain.quest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.master.entity.SubQuest;

@Repository
public interface SubQuestRepository extends JpaRepository<SubQuest, Long> {
}
