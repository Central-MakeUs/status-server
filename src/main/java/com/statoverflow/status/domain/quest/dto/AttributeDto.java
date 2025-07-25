package com.statoverflow.status.domain.quest.dto;

import java.util.ArrayList;
import java.util.List;

import com.statoverflow.status.domain.master.entity.MainSubQuest;

public record AttributeDto(
	int id,
	String name,
	int exp
) {

	public static List<AttributeDto> fromEntity(MainSubQuest mainSubQuest){
		List<AttributeDto> attributeDtos = new ArrayList<>();

		attributeDtos.add(new AttributeDto(
			mainSubQuest.getAttribute1().getId(),
			mainSubQuest.getAttribute1().getName(),
			mainSubQuest.getExp1()
		));

		if (mainSubQuest.getAttribute2() != null) {
			attributeDtos.add(new AttributeDto(
				mainSubQuest.getAttribute2().getId(),
				mainSubQuest.getAttribute2().getName(),
				mainSubQuest.getExp2()
			));
		}
		return attributeDtos;
	}
}