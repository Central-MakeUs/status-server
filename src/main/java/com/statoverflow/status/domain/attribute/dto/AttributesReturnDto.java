package com.statoverflow.status.domain.attribute.dto;

import com.statoverflow.status.domain.master.enums.AttributeType;
import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;


public record AttributesReturnDto(
    Integer attributeId,
    String name,
    AttributeType type,
    String description,
    Integer level,
    Integer exp) {

    public static AttributesReturnDto fromEntity(UsersAttributeProgress entity) {
        return new AttributesReturnDto(
                entity.getAttribute().getId(),
                entity.getAttribute().getName(),
                entity.getAttribute().getType(),
                entity.getAttribute().getDescription(),
                entity.getLevel(),
                entity.getExp()
        );
    }

}
