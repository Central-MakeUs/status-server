package com.statoverflow.status.domain.quest.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AttributeIdDto(
	@NotNull(message = "Attributes list cannot be null")
	@NotEmpty(message = "Attributes list cannot be empty")
	List<Integer> attributes
) {}