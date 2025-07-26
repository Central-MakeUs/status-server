package com.statoverflow.status.domain.quest.service.interfaces;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.request.RerollSubQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;

public interface SubQuestService {


	List<SubQuestResponseDto> getSubQuests(List<Integer> attributes, Long mainQuest, Long id);

	List<SubQuestResponseDto> rerollSubQuestRequestDto(RerollSubQuestRequestDto dto, Long id);
}
