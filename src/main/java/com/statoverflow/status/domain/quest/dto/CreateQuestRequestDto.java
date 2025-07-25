package com.statoverflow.status.domain.quest.dto;

import java.time.LocalDate;
import java.util.List;

import com.statoverflow.status.domain.quest.enums.FrequencyType;

public record CreateQuestRequestDto(
	Long theme,
	Long mainQuest,
	LocalDate startDate,
	LocalDate endDate,
	List<SubQuestInfo> subQuests
) {

	public record SubQuestInfo(
		Long id,
		FrequencyType frequencyType,
		int actionUnitNum
	) {}
}
