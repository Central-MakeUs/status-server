package com.statoverflow.status.domain.quest.service;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.RerollSubQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.SubQuestResponseDto;

public interface SubQuestService {


	List<SubQuestResponseDto> getSubQuests(List<Integer> attributes, Long mainQuest, Long id);

	List<SubQuestResponseDto> rerollSubQuestRequestDto(RerollSubQuestRequestDto dto, Long id);
}
