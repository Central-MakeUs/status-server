package com.statoverflow.status.domain.quest.dto.response;

import com.statoverflow.status.domain.quest.dto.AttributeDto;

import java.util.List;

public record SubQuestResponseDto(Long id,
								  String frequencyType,
								  String actionUnitType,
								  int actionUnitNum,
								  List<AttributeDto> attributes,
								  String desc) {

	public record UsersSubQuestResponseDto(
			SubQuestResponseDto subQuestInfo,
			int repeatCnt,
			boolean essential
	){};
}
