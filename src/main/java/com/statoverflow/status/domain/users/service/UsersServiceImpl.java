package com.statoverflow.status.domain.users.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.ProviderType;
import com.statoverflow.status.domain.users.repository.UsersRepository;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UsersServiceImpl implements UsersService{

	private final UsersRepository usersRepository;

	@Override
	public BasicUsersDto getUsersByProvider(OAuthProviderDto provider) {
		log.info("getUsersByProvider");

		Users users = usersRepository.findByProviderTypeAndProviderId(provider.providerType(), provider.providerId())
			.orElseGet(() -> {

				log.info("회원가입 필요");
				throw new CustomException(ErrorType.USER_NOT_FOUND, provider);

			});
		return BasicUsersDto.from(users);
	}

	@Override
	public BasicUsersDto signUp(SignUpRequestDto req) {

		log.info("회원가입 시작");
		Users user = req.toEntity();

		// todo: users_attribute_progress 내 정보 추가

		return BasicUsersDto.from(usersRepository.save(user));
	}
}
