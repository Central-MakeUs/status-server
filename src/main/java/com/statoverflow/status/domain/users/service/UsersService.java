package com.statoverflow.status.domain.users.service;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;


public interface UsersService {
	BasicUsersDto getUsersByProvider(OAuthProviderDto provider);

	BasicUsersDto signUp(SignUpRequestDto req);
}
