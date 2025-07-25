package com.statoverflow.status.domain.quest.dto;

import java.time.LocalDate;
import java.util.List;

public record CreateQuestResponseDto(
	Long id,
	LocalDate startDate,
	LocalDate endDate,
	int totalWeeks,
	String title,
	List<AttributeDto> attributes,
	List<SubQuestResponseDto> subQuests
) {
}
