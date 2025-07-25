package com.statoverflow.status.domain.quest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.statoverflow.status.domain.master.entity.QuestTheme;

@Repository
public interface ThemeRepository extends JpaRepository<QuestTheme, Long> {

	@Query(value = "SELECT * FROM quest_theme qt WHERE (qt.linked_attribute & :selectedAttributes) > 0", nativeQuery = true)
	List<QuestTheme> findAllByAttributes(@Param("selectedAttributes") int selectedAttributes);


}
