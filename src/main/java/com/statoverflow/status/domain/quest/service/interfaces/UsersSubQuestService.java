package com.statoverflow.status.domain.quest.service.interfaces;

import java.util.List;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.quest.dto.SubQuestLogDto;
import com.statoverflow.status.domain.quest.dto.response.DoSubQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.QuestHistoryByDateDto;
import com.statoverflow.status.domain.quest.dto.response.SubQuestResponseDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.entity.UsersSubQuestLog;

public interface UsersSubQuestService {
	List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long id);
	List<SubQuestResponseDto.UsersSubQuestResponseDto> getTodaySubQuests(Long userId, Long mainQuestId);

	List<QuestHistoryByDateDto> getSubQuestsLogs(Long userId, Long mainQuestId);

	DoSubQuestResponseDto doSubQuest(Long userId, SubQuestLogDto dto);

	SubQuestLogDto editSubQuest(Long id, SubQuestLogDto dto);

	void checkMainQuestCompleted(UsersMainQuest usersMainQuest);

}
