package com.statoverflow.status.domain.quest.service.interfaces;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;

public interface UsersSubQuestService {
	List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long id);
	List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long userId, Long mainQuestId);
}
