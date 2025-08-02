package com.statoverflow.status.domain.quest.service.interfaces;

import java.util.List;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.SubQuestLogDto;
import com.statoverflow.status.domain.quest.dto.response.QuestHistoryByDateDto;
import com.statoverflow.status.domain.quest.dto.response.RewardResponseDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;

public interface UsersSubQuestService {
	List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long id);
	List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long userId, Long mainQuestId);

	List<QuestHistoryByDateDto> getSubQuestsLogs(Long userId, Long mainQuestId);

	RewardResponseDto doSubQuest(Long userId, SubQuestLogDto dto);

	SubQuestLogDto editSubQuest(Long id, SubQuestLogDto dto);
}