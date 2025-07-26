package com.statoverflow.status.domain.quest.service.interfaces;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.request.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.CreateQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.UsersMainQuestResponseDto;

public interface UsersMainQuestService {
	CreateQuestResponseDto create(CreateQuestRequestDto dto, Long id);
	void deleteMainQuest(Long mainQuestId);
	List<UsersMainQuestResponseDto> getUsersMainQuests(Long id);
}
