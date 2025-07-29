package com.statoverflow.status.domain.users.service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.statoverflow.status.domain.attribute.repository.AttributeRepository;
import com.statoverflow.status.domain.attribute.repository.UsersAttributeProgressRepository;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.SignUpRequestDto;
import com.statoverflow.status.domain.auth.dto.SocialLoginReturnDto;
import com.statoverflow.status.domain.master.entity.Attribute;
import com.statoverflow.status.domain.master.entity.TermsAndConditions;
import com.statoverflow.status.domain.master.enums.TermsType;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import com.statoverflow.status.domain.users.entity.UsersAgreements;
import com.statoverflow.status.domain.users.repository.TermsAndConditionsRepository;
import com.statoverflow.status.domain.users.entity.Users;
import com.statoverflow.status.domain.users.entity.UsersAttributeProgress;
import com.statoverflow.status.domain.users.enums.AccountStatus;
import com.statoverflow.status.domain.users.repository.UsersAgreementsRepository;
import com.statoverflow.status.domain.users.repository.UsersRepository;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UsersServiceImpl implements UsersService{

	private final UsersRepository usersRepository;
	private final UsersAttributeProgressRepository	usersAttributeProgressRepository;
	private final AttributeRepository attributeRepository;

	@Value("${status.users.users-service.characters}")
	private String VALID_CHARACTERS;

	@Value("${status.users.users-service.length}")
	private int TAG_LENGTH;

	private final TermsAndConditionsRepository termsAndConditionsRepository;
	private final UsersAgreementsRepository usersAgreementsRepository;

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

		log.debug("회원가입 시작, req: {}", req);
		Users user = req.toEntity();

		// 닉네임에 고유  Tag 생성
		String tag = generateTagForNickname(req.nickname());
		log.debug("닉네임에 따른 랜덤 Tag 생성: {}", tag);
		user.setTag(tag);

		usersRepository.save(user);
	
		// 모든 마스터 Attribute에 대해 초기 UsersAttributeProgress 생성 및 저장
		initializeUserAttributes(user);

		agreeToLatestRequiredTerms(user);

		log.debug("회원가입 완료: {}", user.getId());
		return BasicUsersDto.from(user);
	}

	private void agreeToLatestRequiredTerms(Users user) {
		// 현재 유효한 모든 필수 약관의 최신 버전을 조회
		List<TermsAndConditions> validTerms = termsAndConditionsRepository.findAllLatestEssentialEffectiveByEachType(
			LocalDate.now());

		List<UsersAgreements> usersAgreements = new ArrayList<>();
		validTerms.forEach(
			termsAndConditions -> {
				usersAgreements.add(UsersAgreements.builder()
					.user(user)
					.terms(termsAndConditions)
					.build());
			}
		);
		usersAgreementsRepository.saveAll(usersAgreements);

		log.debug("사용자 {}가 최신 필수 약관 {} 개에 동의 처리되었습니다.", user.getId(), usersAgreements.size());

	}

	@Override
	public BasicUsersDto updateNickname(Long userId, String nickname) {
		Users user = usersRepository.findById(userId)
			.orElseThrow(() -> new CustomException(ErrorType.RESOURCE_NOT_FOUND));

		if(user.getNickname() != null && user.getNickname().equals(nickname)) {
			throw new CustomException(ErrorType.NICKNAME_NOT_CHANGED);
		}

		user.setTag(generateTagForNickname(nickname));
		user.setNickname(nickname);

		return BasicUsersDto.from(user);
	}

	@Override
	public void deleteUser(Long id, HttpServletResponse response) {
		// providerId 날리기
		Users user = usersRepository.findById(id).orElseThrow(() -> new CustomException(ErrorType.RESOURCE_NOT_FOUND));
		user.setStatus(AccountStatus.INACTIVE);
		user.setProviderId("");
		usersRepository.save(user);
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

	private String generateTagForNickname(String nickname) {
		String generatedTag;
		boolean isDuplicate;
		SecureRandom random = new SecureRandom(); // 보안적으로 강력한 난수 생성기

		int charactersLength = VALID_CHARACTERS.length();

		// 무작위로 태그를 생성하고 중복을 확인
		do {
			StringBuilder tagBuilder = new StringBuilder();
			for (int i = 0; i < TAG_LENGTH; i++) {
				tagBuilder.append(VALID_CHARACTERS.charAt(random.nextInt(charactersLength)));
			}
			generatedTag = tagBuilder.toString();

			isDuplicate = usersRepository.existsByNicknameAndTag(nickname, generatedTag);

		} while (isDuplicate); // 중복이 발생하면 다시 생성

		return generatedTag;
	}
}
