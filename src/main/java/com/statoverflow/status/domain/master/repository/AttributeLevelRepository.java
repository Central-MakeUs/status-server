package com.statoverflow.status.domain.master.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statoverflow.status.domain.master.entity.AttributeLevel;
import com.statoverflow.status.domain.master.entity.AttributeLevelId;

public interface AttributeLevelRepository extends JpaRepository<AttributeLevel, AttributeLevelId> {
}
