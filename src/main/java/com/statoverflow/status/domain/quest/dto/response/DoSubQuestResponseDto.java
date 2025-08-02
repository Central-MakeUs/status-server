package com.statoverflow.status.domain.quest.dto.response;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.quest.entity.UsersMainQuest;

import java.util.List;

public record DoSubQuestResponseDto(
        List<AttributeDto> returns,
        UsersMainQuest mainQuest
) {
}
