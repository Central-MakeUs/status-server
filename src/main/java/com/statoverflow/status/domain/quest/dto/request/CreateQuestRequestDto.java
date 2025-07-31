package com.statoverflow.status.domain.quest.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.statoverflow.status.domain.quest.enums.FrequencyType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateQuestRequestDto(
	Long theme,
	Long mainQuest,

	@NotNull(message = "시작일은 필수입니다.")
	@FutureOrPresent(message = "시작일은 오늘 이후여야 합니다.")
	LocalDate startDate,

	@NotNull(message = "종료일은 필수입니다.")
	@FutureOrPresent(message = "종료일은 오늘 이후여야 합니다.")
	LocalDate endDate,

	@Valid // <-- 리스트 내부 객체에 대한 유효성 검사 수행
	@Size(min = 1, max = 3, message = "서브 퀘스트는 최소 1개 이상이어야 합니다.")
	@NotNull(message = "서브 퀘스트 목록은 필수입니다.")
	List<SubQuestInfo> subQuests
) {

	public record SubQuestInfo(

		@NotNull(message = "서브 퀘스트 ID는 필수입니다.")
		Long id,

		@NotNull(message = "FrequencyType은 필수입니다.")
		FrequencyType frequencyType,

		@Positive(message = "actionUnitNum은 양수여야 합니다.")
		int actionUnitNum
	) {}
}
