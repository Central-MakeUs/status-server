package com.statoverflow.status.domain.quest.service;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.ThemeResponseDto;

public interface QuestService {
	List<ThemeResponseDto> getThemes(List<Integer> attributes);

	List<ThemeResponseDto> rerollThemes(List<Integer> attributes, List<Integer> themes);
}
