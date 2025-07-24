package com.statoverflow.status.domain.quest.service;

import static com.statoverflow.status.global.error.ErrorType.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.QuestTheme;
import com.statoverflow.status.domain.quest.dto.ThemeResponseDto;
import com.statoverflow.status.domain.quest.repository.ThemeRepository;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

@Service
public class QuestServiceImpl implements QuestService{

	private final AttributeRepository attributeRepository;
	private final ThemeRepository themeRepository;

	public QuestServiceImpl(AttributeRepository attributeRepository, ThemeRepository themeRepository) {
		this.attributeRepository = attributeRepository;
		this.themeRepository = themeRepository;
	}

	@Override
	public List<ThemeResponseDto> getThemes(List<Integer> attributes) {

		if(attributes.size() > 2 || attributes.isEmpty()) {
			throw new CustomException(ErrorType.INVALID_ATTRIBUTES);
		}

		int combinedBitmask = attributes.stream()
			.mapToInt(attributeId ->
				attributeRepository.findById(attributeId)
					.map(Attribute::getBitMask)
					.orElse(0)
			)
			.reduce(0, (acc, bitMask) -> acc | bitMask);

		List<QuestTheme> questThemes = themeRepository.findAllByLinkedAttributeIntersection(combinedBitmask);

		return questThemes.stream()
			.map(questTheme -> new ThemeResponseDto(questTheme.getId(), questTheme.getName()))
			.collect(Collectors.toList());
	}
}
