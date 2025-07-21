package com.statoverflow.status.domain.users.service;

import org.springframework.stereotype.Service;

import com.statoverflow.status.domain.auth.dto.OAuthLoginRequestDto;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.enums.ProviderType;
import com.statoverflow.status.domain.users.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService{

	private final UsersRepository usersRepository;

	@Override
	public BasicUsersDto getUsersByProvider(String providerType, String providerId) {
		log.info("getUsersByProvider");
		Users users = usersRepository.findByProviderTypeAndProviderId(ProviderType.KAKAO, providerId)
			.orElseGet(() -> {
				log.info("회원가입");
					Users newUser = Users.builder()
						.providerType(ProviderType.KAKAO)
						.providerId(String.valueOf(providerId)) // Long을 String으로 변환
						.uuid("1231")
						.nickname("테스트")
						.build();
					usersRepository.save(newUser);
					log.info("user: {}", newUser.toString());
					return newUser;
			});
		return BasicUsersDto.from(users);
	}
}
