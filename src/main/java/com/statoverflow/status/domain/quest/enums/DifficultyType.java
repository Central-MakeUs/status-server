package com.statoverflow.status.domain.quest.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DifficultyType {
	EASY("easy", "하품 나오는 일상"),
	NORMAL("normal", "적당한 임무"),
	HARD("hard","전설적인 도전");

	private final String field, description;
}
