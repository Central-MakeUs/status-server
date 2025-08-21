package com.statoverflow.status.domain.attribute.service;

import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.attribute.dto.AttributesReturnDto;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.SourceType;

import java.util.List;

public interface AttributeService {
    public List<AttributesReturnDto> getAttributes(Long userId);

    <T> void addExp(Users users, List<AttributeDto> attributeDtos, T sourceType);
}
