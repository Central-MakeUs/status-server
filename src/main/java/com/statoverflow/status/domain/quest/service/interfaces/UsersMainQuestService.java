package com.statoverflow.status.domain.quest.service.interfaces;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.request.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.CreateQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.UsersMainQuestResponseDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;

public interface UsersMainQuestService {
	CreateQuestResponseDto create(CreateQuestRequestDto dto, Long id);
	void deleteMainQuest(Long mainQuestId);
	List<UsersMainQuestResponseDto> getUsersMainQuests(Long id);
	List<UsersMainQuest> getUsersMainQuestByUserId(Long userId);
}
