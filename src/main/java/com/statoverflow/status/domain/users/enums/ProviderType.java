package com.statoverflow.status.domain.users.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProviderType {
	KAKAO("kakao"), GOOGLE("google"), APPLE("apple"), GUEST("guest");

	private final String field;
}
