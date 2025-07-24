package com.statoverflow.status.domain.users.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProviderType {
	KAKAO("kakao"), GOOGLE("google"), APPLE("apple");

	private final String field;
}
