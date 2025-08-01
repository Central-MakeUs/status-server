package com.statoverflow.status.domain.quest.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QuestStatus {
	ACTIVE("active"),
	INACTIVE("inactive"),
	COMPLETED("completed"),
	DELETED("deleted"),
	WEEKLY_COMPLETED("weekly_completed"),
	ENDED("ended");

	private final String field;
}
