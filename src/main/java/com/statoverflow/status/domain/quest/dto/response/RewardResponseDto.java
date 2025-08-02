package com.statoverflow.status.domain.quest.dto.response;

import java.util.List;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;


public record RewardResponseDto(
	List<AttributeDto> subQuestRewards,
	List<AttributeDto> mainQuestRewards,
	boolean isMainQuestCompleted
) {}
