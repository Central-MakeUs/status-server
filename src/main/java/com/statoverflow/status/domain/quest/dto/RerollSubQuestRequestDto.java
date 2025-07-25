package com.statoverflow.status.domain.quest.dto;

import java.util.List;

public record RerollSubQuestRequestDto(
	Long mainQuest,
	List<Long> attributes,
	List<Long> selectedSubQuests,
	List<Long> gottenSubQuests
) {
}
