package com.statoverflow.status.domain.users.service;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.ProviderType;
import com.statoverflow.status.domain.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService{

	private final UsersRepository usersRepository;

	@Override
	public BasicUsersDto getUsersByProvider(String providerType, String providerId) {
		Users users = usersRepository.findByProviderTypeAndProviderId(ProviderType.valueOf(providerType), providerId);
		return null;
	}
}
