package com.statoverflow.status.domain.quest.service;

import com.statoverflow.status.domain.quest.dto.request.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.CreateQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;

import java.util.List;

public interface UserQuestService {
	CreateQuestResponseDto create(CreateQuestRequestDto dto, Long id);
	List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long id);
}
