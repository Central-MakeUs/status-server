package com.statoverflow.status.domain.users.service;

import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.attribute.repository.UsersAttributeProgressRepository;
import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;
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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UsersServiceImpl implements UsersService{

	private final UsersRepository usersRepository;
	private final UsersAttributeProgressRepository	usersAttributeProgressRepository;
	private final AttributeRepository attributeRepository;

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

		usersRepository.save(user); // 2. Users 엔티티 저장

		// todo: users_attribute_progress 내 정보 추가
		// 모든 마스터 Attribute에 대해 초기 UsersAttributeProgress 생성 및 저장
		initializeUserAttributes(user);


		log.info("회원가입 완료: {}", user.getId());
		return BasicUsersDto.from(user);
	}

	@Override
	public void updateNickname(Long userId, String nickname) {
		Users user = usersRepository.findById(userId)
			.orElseThrow(() -> new CustomException(ErrorType.RESOURCE_NOT_FOUND));

		if(user.getNickname() != null && user.getNickname().equals(nickname)) {
			throw new CustomException(ErrorType.NICKNAME_NOT_CHANGED);
		}
		user.setNickname(nickname);
		
		// todo: tag 바꾸는 작업 실행
	}

	private void initializeUserAttributes(Users user) {
		List<Attribute> allAttributes = attributeRepository.findAll();

		List<UsersAttributeProgress> initialProgresses = allAttributes.stream()
				.map(attribute -> UsersAttributeProgress.builder()
						.user(user)
						.attribute(attribute)
						.build())
				.collect(Collectors.toList());

		usersAttributeProgressRepository.saveAll(initialProgresses);
	}
}
