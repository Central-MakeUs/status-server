package com.statoverflow.status.domain.quest.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum QuestStatus {
	ACTIVE("active"),
	FAILED("failed"), // 퀘스트 기간이 끝났으나 목표를 달성하지 못한 상태
	DELETED("deleted"), // 유저가 삭제했을 시

	WEEKLY_ACCOMPLISHED("weekly_accomplished"), // 해당 주간 목표를 다 달성한 상태
	COMPLETED("completed"), // 전체 달성 완료
	ACCOMPLISHED("accomplished"), // (주간/월간) 주기적인 목표를 달성한 상태(당일 상태 체크용)


	;
	private final String field;
}
