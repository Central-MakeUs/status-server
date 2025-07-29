package com.statoverflow.status.domain.users.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.statoverflow.status.domain.master.entity.TermsAndConditions;

public interface TermsAndConditionsRepository extends JpaRepository<TermsAndConditions, Long> {

	@Query("SELECT t FROM TermsAndConditions t " +
		"WHERE t.isEssential = TRUE AND " +
		"t.effectiveDate = (SELECT MAX(t2.effectiveDate) FROM TermsAndConditions t2 WHERE t2.type = t.type AND t2.effectiveDate <= :checkDate AND t2.isEssential = TRUE) AND " +
		"t.createdAt = (SELECT MAX(t3.createdAt) FROM TermsAndConditions t3 WHERE t3.type = t.type AND t3.effectiveDate = t.effectiveDate AND t3.isEssential = TRUE)")
	List<TermsAndConditions> findAllLatestEssentialEffectiveByEachType(LocalDate checkDate);

}
