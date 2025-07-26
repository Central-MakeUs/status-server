package com.statoverflow.status.domain.quest.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.statoverflow.status.domain.quest.enums.DifficultyType;

public record QuestHistoryByDateDto(
	LocalDate date,
	List<SubQuestLogsResponseDto> logs
) {

	public record SubQuestLogsResponseDto(
		SubQuestResponseDto userSubQuest,
		SubQuestLog log
	) {
		public record SubQuestLog(
			Long id,
			DifficultyType difficulty,
			String memo
		) {
		}
	}
}