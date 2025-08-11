package com.statoverflow.status.domain.master.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConfirmType {
	MOOD_LOG("mood_log"),
	PHOTO_LOG("photo_log"),;

	private final String field;
}
