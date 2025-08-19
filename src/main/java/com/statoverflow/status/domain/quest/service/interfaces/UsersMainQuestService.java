package com.statoverflow.status.domain.quest.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.statoverflow.status.domain.quest.dto.WithStatus;
import com.statoverflow.status.domain.quest.dto.request.CreateQuestRequestDto;
import com.statoverflow.status.domain.quest.dto.response.CreateQuestResponseDto;
import com.statoverflow.status.domain.quest.dto.response.UserQuestStatisticsDto;
import com.statoverflow.status.domain.quest.dto.response.UsersMainQuestResponseDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;
import com.statoverflow.status.domain.quest.enums.QuestStatus;

public interface UsersMainQuestService {
	CreateQuestResponseDto create(CreateQuestRequestDto dto, Long id);
	void deleteMainQuest(Long mainQuestId);
	List<UsersMainQuestResponseDto> getUsersMainQuests(Long id);
	List<UsersMainQuest> getUsersMainQuestByUserIdAndStatus(Long userId, List<QuestStatus> statuses, Sort sort);
	WithStatus<UsersMainQuestResponseDto> getUsersMainQuestById(Long userId, Long mainQuestId);

	UserQuestStatisticsDto getUserStatistics(Long userId);

	List<WithStatus<UsersMainQuestResponseDto>> getUsersMainQuestHistory(Long userId);
}
