package com.statoverflow.status.domain.users.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SourceType {
	MAINQUEST("main_quest"), SUBQUESTLOG("sub_quest_log");

	private final String field;
}
