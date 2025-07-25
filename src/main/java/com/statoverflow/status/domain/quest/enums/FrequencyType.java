package com.statoverflow.status.domain.quest.enums;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FrequencyType {
	DAILY("daily", "매일", 1, 1),
	WEEKLY_1("weekly_1", "주 1회", 1, 7),
	WEEKLY_2("weekly_2", "주 2회", 2, 7),
	WEEKLY_3("weekly_3", "주 3회", 3, 7),
	WEEKLY_4("weekly_4", "주 4회", 4, 7),
	WEEKLY_5("weekly_5", "주 5회", 5, 7),
	WEEKLY_6("weekly_6", "주 6회", 6, 7),
	MONTHLY_1("monthly_1", "월 1회", 1, -1),
	MONTHLY_2("monthly_2", "월 2회", 2, -1),
	MONTHLY_3("monthly_3", "월 3회", 3, -1),
	MONTHLY_4("monthly_4", "월 4회", 4, -1);

	private static final Random RANDOM = new Random();
	private static final FrequencyType[] VALUES = values();

	private final String field, description;
	private final int cnt, per;


	public static FrequencyType getRandomFrequencyType() {
		return VALUES[RANDOM.nextInt(VALUES.length)];
	}
}
