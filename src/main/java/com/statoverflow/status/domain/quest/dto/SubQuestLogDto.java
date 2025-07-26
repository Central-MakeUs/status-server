package com.statoverflow.status.domain.quest.dto;

import com.statoverflow.status.domain.quest.enums.DifficultyType;

public record SubQuestLogDto(
	Long id,
	DifficultyType difficulty,
	String memo
) {
}