package com.statoverflow.status.domain.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.statoverflow.status.domain.attribute.dto.AttributeDto;
import com.statoverflow.status.domain.attribute.dto.AttributesReturnDto;
import com.statoverflow.status.domain.attribute.service.AttributeService;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.master.entity.TierLevel;
import com.statoverflow.status.domain.users.repository.TierLevelRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class WithTier<T extends SocialLoginReturnDto> implements SocialLoginReturnDto {

	private final AttributeService attributeService;
	private final TierLevelRepository tierLevelRepository;

	@JsonUnwrapped T data;
	TierDto tier;

	@Override
	@JsonProperty("type")
	public String type() { return data.type(); }


}
