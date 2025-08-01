package com.statoverflow.status.domain.quest.dto.response;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.quest.enums.FrequencyType;

import java.util.List;

public record SubQuestResponseDto(Long id,
								  FrequencyType frequencyType,
								  String actionUnitType,
								  int actionUnitNum,
								  List<AttributeDto> attributes,
								  String desc) {


	public record UsersSubQuestResponseDto(
		SubQuestResponseDto subQuestInfo,
		int repeatCnt, // repeatCnt 회 남음
		boolean essential // 필수 여부
	){}

}
