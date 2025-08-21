package com.statoverflow.status.domain.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.ProviderType;


public record BasicUsersDto(Long id, String nickname, ProviderType.LoginType providerType, TierDto tier) implements SocialLoginReturnDto {

	public static BasicUsersDto from(Users user, TierDto tier) {
		return new BasicUsersDto(
			user.getId(),
			user.getNickname(),
			user.getProviderType().getField(),
			tier
		);
	}

	public static BasicUsersDto of(Long id, String nickname, String providerType, TierDto tier) {
		return new BasicUsersDto(id, nickname, toLoginType(providerType), tier);
	}

	private static ProviderType.LoginType toLoginType(String s) {
		return ProviderType.LoginType.valueOf(s.toUpperCase());
	}

	@Override
	@JsonProperty("type")
	public String type() {
		return "LOGIN";
	}

	public String toString() {
		return "BasicUsersDto{" +
			"id=" + id +
			", nickname='" + nickname + '\'' +
			'}';
	}
};
