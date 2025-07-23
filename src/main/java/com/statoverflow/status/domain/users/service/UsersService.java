package com.statoverflow.status.domain.users.service;

import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;


public interface UsersService {
	SocialLoginReturnDto getUsersByProvider(OAuthProviderDto provider);

	BasicUsersDto signUp(SignUpRequestDto req);
}
