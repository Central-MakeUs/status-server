package com.statoverflow.status.domain.quest.dto;

import java.util.List;

public record RerollSubQuestRequestDto(
	Long mainQuest,
	List<Integer> attributes,
	List<Long> selectedSubQuests,
	List<Long> gottenSubQuests
) {
}
