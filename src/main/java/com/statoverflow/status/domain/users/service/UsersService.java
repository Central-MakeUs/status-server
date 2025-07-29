package com.statoverflow.status.domain.users.service;

import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.dto.NicknameRequestDto;

import jakarta.servlet.http.HttpServletResponse;

public interface UsersService {
	SocialLoginReturnDto getUsersByProvider(OAuthProviderDto provider);

	BasicUsersDto signUp(SignUpRequestDto req);

    BasicUsersDto updateNickname(Long userId, String nickname);

	void deleteUser(Long id, HttpServletResponse response);
}
