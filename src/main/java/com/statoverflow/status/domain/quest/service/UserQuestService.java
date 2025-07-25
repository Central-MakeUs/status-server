package com.statoverflow.status.domain.quest.service;

import com.statoverflow.status.domain.quest.dto.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.CreateQuestResponseDto;

public interface UserQuestService {
	CreateQuestResponseDto create(CreateQuestRequestDto dto, Long id);
}
