package com.statoverflow.status.domain.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.master.enums.Tier;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.ProviderType;

import lombok.ToString;


public record BasicUsersDto(Long id, String nickname, ProviderType.LoginType providerType, TierDto tier) implements SocialLoginReturnDto {
	public static BasicUsersDto from(Users user) {
		return new BasicUsersDto(
			user.getId(),
			user.getNickname(),
			user.getProviderType().getField(),
			new TierDto(Tier.BRONZE, 1)
		);
	}

	public static BasicUsersDto of(Long id, String nickname, String providerType) {
		return new BasicUsersDto(id, nickname, toLoginType(providerType), new TierDto(Tier.BRONZE, 1));
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
