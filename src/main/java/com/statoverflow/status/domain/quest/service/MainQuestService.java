package com.statoverflow.status.domain.quest.service;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.response.MainQuestResponseDto;

public interface MainQuestService {

	List<MainQuestResponseDto> getMainQuests(List<Integer> attributes, Long userId, Long theme);

	List<MainQuestResponseDto> rerollMainQuests(List<Integer> attributes, List<Long> mainQuests, Long userId, Long theme);

}
