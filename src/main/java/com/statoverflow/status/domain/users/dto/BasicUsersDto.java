package com.statoverflow.status.domain.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.users.entity.Users;
import lombok.ToString;


public record BasicUsersDto(Long id, String nickname) implements SocialLoginReturnDto {
	public static BasicUsersDto from(Users user) {
		return new BasicUsersDto(
			user.getId(),
			user.getNickname()
		);
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
