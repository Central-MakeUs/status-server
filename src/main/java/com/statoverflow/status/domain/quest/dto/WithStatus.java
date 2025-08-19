package com.statoverflow.status.domain.quest.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.statoverflow.status.domain.quest.enums.QuestStatus;

public record WithStatus<T>(
	@JsonUnwrapped
	T data,
	QuestStatus status
) {
		public static <T> WithStatus<T> of(T data, QuestStatus status) {
			return new WithStatus<>(data, status);
		}
	}