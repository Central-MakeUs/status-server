package com.statoverflow.status.domain.users.dto;

import com.statoverflow.status.domain.users.entity.Users;

public record BasicUsersDto(Long id, String nickname) {
	public static BasicUsersDto from(Users user) {
		return new BasicUsersDto(
			user.getId(),
			user.getNickname()
		);
	}

};
