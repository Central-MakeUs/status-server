package com.statoverflow.status.domain.auth.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.statoverflow.status.domain.auth.dto.AppleTokenResponseDto;
import com.statoverflow.status.domain.auth.jwt.AppleClientSecretGenerator;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppleOAuthClient {
	@Value("${status.oauth.apple.client.service-id}")
	private String serviceId;

	@Value("${spring.security.oauth2.client.registration.apple.redirect-uri}")
	private String appleRedirectUri;

	@Value("${spring.security.oauth2.client.provider.apple.token-uri}")
	private String appleTokenEndpoint;

	private final RestTemplate restTemplate = new RestTemplate();
	private final AppleClientSecretGenerator appleClientSecretGenerator;

    // todo: 토큰 검증 API 실행
	public AppleTokenResponseDto getAppleTokens(String authorizationCode) {
		log.debug("Apple 토큰 교환 요청 시작...");

		String clientSecret = appleClientSecretGenerator.generateClientSecret();
		String decodedCode = URLDecoder.decode(authorizationCode, StandardCharsets.UTF_8);


		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", serviceId);
		params.add("client_secret", clientSecret);
		params.add("code", decodedCode);
		params.add("redirect_uri", appleRedirectUri);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
		ParameterizedTypeReference<AppleTokenResponseDto> responseType = new ParameterizedTypeReference<>() {
		};
		try {

			ResponseEntity<AppleTokenResponseDto> response = restTemplate.exchange(
				appleTokenEndpoint,
				HttpMethod.POST,
				request,
				responseType
			);

			log.debug("애플 액세스 토큰 수신 완료. 응답 상태 코드: {}", response.getStatusCode());
			return response.getBody();

		} catch (Exception e) {
			log.error("애플 액세스 토큰 교환 실패: {}", e.getMessage(), e);
			throw new CustomException(ErrorType.DEFAULT_ERROR);
		}

	}

}
