package com.statoverflow.status.domain.quest.service.interfaces;

import java.util.List;

import com.statoverflow.status.domain.quest.dto.response.ThemeResponseDto;

public interface ThemeService {

	List<ThemeResponseDto> getThemes(List<Integer> attributes);

	List<ThemeResponseDto> rerollThemes(List<Integer> attributes, List<Integer> themes);


}
