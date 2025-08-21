package com.statoverflow.status.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statoverflow.status.domain.master.entity.TierLevel;

public interface TierLevelRepository extends JpaRepository<TierLevel, Long> {
	TierLevel findTopByXpRequiredGreaterThanOrderByXpRequiredAsc(Long xpRequired);
}
