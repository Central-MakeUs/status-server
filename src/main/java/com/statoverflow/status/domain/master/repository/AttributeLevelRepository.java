package com.statoverflow.status.domain.master.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statoverflow.status.domain.master.entity.AttributeLevel;
import com.statoverflow.status.domain.master.entity.AttributeLevelId;
import com.statoverflow.status.domain.master.enums.AttributeType;

public interface AttributeLevelRepository extends JpaRepository<AttributeLevel, AttributeLevelId> {

	AttributeLevel findTopByIdTypeAndXpRequiredGreaterThanOrderByXpRequiredAsc(AttributeType type, Long totalExp);

	Optional<AttributeLevel> findByIdTypeAndIdLevel(AttributeType type, Integer level);
}
