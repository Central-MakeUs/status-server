package com.statoverflow.status.domain.users.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UsersServiceImpl implements UsersService{

	private final UsersRepository usersRepository;

	@Override
	public SocialLoginReturnDto getUsersByProvider(OAuthProviderDto provider) {
		return usersRepository.findByProviderTypeAndProviderId(
				provider.providerType(), provider.providerId()
			)
			.<SocialLoginReturnDto>map(BasicUsersDto::from)
			.orElse(provider);
	}
	@Override
	public BasicUsersDto signUp(SignUpRequestDto req) {

		log.info("회원가입 시작");
		Users user = req.toEntity();

		// todo: users_attribute_progress 내 정보 추가

		return BasicUsersDto.from(usersRepository.save(user));
	}
}
