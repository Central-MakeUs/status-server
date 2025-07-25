package com.statoverflow.status.domain.quest.dto;

import java.util.List;

public record SubQuestResponseDto(Long id,
								  String frequencyType,
								  String actionUnitType,
								  int actionUnitNum,
								  List<AttributeDto> attributes,
								  String desc) {

}
