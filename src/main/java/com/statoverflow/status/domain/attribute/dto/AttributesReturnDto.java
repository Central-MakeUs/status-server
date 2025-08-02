package com.statoverflow.status.domain.attribute.dto;

import com.statoverflow.status.domain.master.entity.AttributeLevel;
import com.statoverflow.status.domain.master.enums.AttributeType;
import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;

import java.util.Map;


public record AttributesReturnDto(
    Integer attributeId,
    String name,
    AttributeType type,
    String description,
    Integer level,
    Integer exp,
    Integer expToNextLevel) {

    public static AttributesReturnDto fromEntity(UsersAttributeProgress entity, Map<AttributeType, Map<Integer, Integer>> levelMap) {
        int currentExp = entity.getExp();
        int currentLevel = entity.getLevel();

        int nextLevelExp = levelMap.get(entity.getAttribute().getType()).get(currentLevel);

        return new AttributesReturnDto(
                entity.getAttribute().getId(),
                entity.getAttribute().getName(),
                entity.getAttribute().getType(),
                entity.getAttribute().getDescription(),
                currentLevel,
                currentExp,
                nextLevelExp - currentExp
        );
    }

}
