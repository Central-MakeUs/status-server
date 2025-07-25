package com.statoverflow.status.domain.quest.enums;

import java.util.Random;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FrequencyType {
	DAILY("daily", "매일"),
	WEEKLY_1("weekly_1", "주 1회"),
	WEEKLY_2("weekly_2", "주 2회"),
	WEEKLY_3("weekly_3", "주 3회"),
	WEEKLY_4("weekly_4", "주 4회"),
	WEEKLY_5("weekly_5", "주 5회"),
	WEEKLY_6("weekly_6", "주 6회"),
	MONTHLY_1("monthly_1", "월 1회"),
	MONTHLY_2("monthly_2", "월 2회"),
	MONTHLY_3("monthly_3", "월 3회"),
	MONTHLY_4("monthly_4", "월 4회");

	private static final Random RANDOM = new Random();
	private static final FrequencyType[] VALUES = values();

	private final String field, description;

	public static FrequencyType getRandomFrequencyType() {
		return VALUES[RANDOM.nextInt(VALUES.length)];
	}
}
