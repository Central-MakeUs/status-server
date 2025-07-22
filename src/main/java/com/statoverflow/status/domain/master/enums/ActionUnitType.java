package com.statoverflow.status.domain.master.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActionUnitType {

	TIME_SECOND("초", 10, 1, 60, true),
	TIME_MINUTE("분", 10, 1, 300, true),
	TIME_HOUR( "시간", 1, 1, 24, true),
	NUMBER_1( "개", 1, 1, 999, true),
	NUMBER_2( "회", 1, 1, 999, true),
	NUMBER_3( "권", 1, 1, 999, true),
	DISTANCE( "km", 1, 1, 999, true),
	ONCE( "회", 1, null, null, false);

	private final String unit;
	private final Integer defaultCount;
	private final Integer minCount;
	private final Integer maxCount;
	private final boolean isCountEditable;

}
