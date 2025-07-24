package com.statoverflow.status.domain.attribute.service;

import com.statoverflow.status.domain.attribute.dto.AttributesReturnDto;

import java.util.List;

public interface AttributeService {
    public List<AttributesReturnDto> getAttributes(Long userId);
}
