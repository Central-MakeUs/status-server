package com.statoverflow.status.domain.master.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum ActionUnitType {

	// Time_Second
	TIME_SECOND("초", 10, 1, 60, true,
		List.of(
			new Range(1, 10, 1.0),
			new Range(11, 30, 1.1),
			new Range(31, 60, 1.2)
		)
	),

	// Time_Minute
	TIME_MINUTE("분", 10, 1, 300, true,
		List.of(
			new Range(1, 10, 1.0),
			new Range(11, 30, 1.1),
			new Range(31, 60, 1.2),
			new Range(61, 120, 1.3),
			new Range(121, 300, 1.5)
		)
	),

	// Time_Hour
	TIME_HOUR("시간", 1, 1, 24, true,
		List.of(
			new Range(1, 3, 1.0),
			new Range(4, 10, 1.1),
			new Range(11, 12, 1.2),
			new Range(13, 24, 1.3)
		)
	),

	// Number_1
	NUMBER_1("개", 1, 1, 999, true,
		List.of(
			new Range(1, 3, 1.0),
			new Range(4, 10, 1.2),
			new Range(11, 30, 1.3),
			new Range(31, 99, 1.4),
			new Range(100, 999, 1.5)
		)
	),

	// Number_2
	NUMBER_2("회", 1, 1, 999, true,
		List.of(
			new Range(1, 3, 1.0),
			new Range(4, 10, 1.2),
			new Range(11, 30, 1.3),
			new Range(31, 99, 1.4),
			new Range(100, 999, 1.5)
		)
	),

	// Number_3
	NUMBER_3("권", 1, 1, 999, true,
		List.of(
			new Range(1, 3, 1.0),
			new Range(4, 10, 1.2),
			new Range(11, 30, 1.3),
			new Range(31, 99, 1.4),
			new Range(100, 999, 1.5)
		)
	),

	// Distance
	DISTANCE("km", 1, 1, 999, true,
		List.of(
			new Range(1, 3, 1.0),
			new Range(4, 10, 1.2),
			new Range(11, 30, 1.3),
			new Range(31, 99, 1.4),
			new Range(100, 999, 1.5)
		)
	),

	// Once - 특수 케이스: 값과 상관없이 1.0 배율 적용
	ONCE("회", 1, null, null, false,
		List.of(new Range(null, null, 1.0))
	);

	private final String unit;
	private final Integer defaultCount;
	private final Integer minCount;
	private final Integer maxCount;
	private final boolean isCountEditable;
	private final List<Range> multipliers; // 새로 추가된 필드

	/**
	 * 사용자의 입력값에 해당하는 경험치 배율을 반환합니다.
	 * @param inputCount 사용자가 입력한 값
	 * @return 해당 범위의 배율, 해당 범위가 없으면 Optional.empty()
	 */
	public Optional<Double> getExpMultiplier(int inputCount) {
		if (this == ONCE) {
			return Optional.of(1.0);
		}

		return this.multipliers.stream()
			.filter(range -> inputCount >= range.min && inputCount <= range.max)
			.map(range -> range.multiplier)
			.findFirst();
	}

	/**
	 * 값의 범위를 저장하는 내부 클래스
	 */
	private record Range(Integer min, Integer max, Double multiplier) {
	}
}