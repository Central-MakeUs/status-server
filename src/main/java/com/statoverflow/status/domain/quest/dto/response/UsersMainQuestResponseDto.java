package com.statoverflow.status.domain.quest.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.statoverflow.status.domain.attribute.dto.AttributeDto;

public record UsersMainQuestResponseDto(
	Long id,
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate startDate,
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate endDate,
	int totalWeeks,
	String title,
	List<AttributeDto> attributes,
	int progress
) {
}
