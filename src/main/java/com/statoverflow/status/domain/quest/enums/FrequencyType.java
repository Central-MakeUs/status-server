package com.statoverflow.status.domain.quest.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FrequencyType {
	DAILY("매일"),
	WEEKLY_1("주 1회"),
	WEEKLY_2("주 2회"),
	WEEKLY_3("주 3회"),
	WEEKLY_4("주 4회"),
	WEEKLY_5("주 5회"),
	WEEKLY_6("주 6회"),
	MONTHLY_1("월 1회"),
	MONTHLY_2("월 2회"),
	MONTHLY_3("월 3회"),
	MONTHLY_4("월 4회");

	private final String description;
}
