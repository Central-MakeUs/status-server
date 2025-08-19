package com.statoverflow.status.domain.quest.dto.response;

public record UserQuestStatisticsDto(
	int totalMainQuests, // 완료한 총 메인 퀘스트 수
	int totalSubQuestVerifications, // 총 서브 퀘스트 인증 횟수
	int averageCompletionRate, // 평균 퀘스트 완료율
	int averageDurationDays // 평균 퀘스트 수행 기간
) {
}
