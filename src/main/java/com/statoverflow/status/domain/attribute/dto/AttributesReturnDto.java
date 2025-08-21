package com.statoverflow.status.domain.attribute.dto;

import com.statoverflow.status.domain.master.entity.Attribute;
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
    Long exp,
    Long expToNextLevel) {

    public static AttributesReturnDto getLevel(UsersAttributeProgress attributeProgress, AttributeLevel levelInfo) {

        return new AttributesReturnDto(
            attributeProgress.getAttribute().getId(),
            attributeProgress.getAttribute().getName(),
            attributeProgress.getAttribute().getType(),
            attributeProgress.getAttribute().getDescription(),
            levelInfo.getId().getLevel(),
            attributeProgress.getTotalExp(),
            levelInfo.getXpRequired() - attributeProgress.getTotalExp()
        );
    }

}
