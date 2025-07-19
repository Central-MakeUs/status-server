package com.statoverflow.status.domain.auth.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper; // ObjectMapper 임포트
import com.statoverflow.status.domain.auth.dto.OAuthUserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType; // MediaType 임포트
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap; // LinkedMultiValueMap 임포트
import org.springframework.util.MultiValueMap; // MultiValueMap 임포트
import org.springframework.web.client.HttpClientErrorException; // HttpClientErrorException 임포트
import org.springframework.web.client.RestTemplate; // RestTemplate 임포트

import static com.statoverflow.status.domain.auth.dto.OAuthUserInfoDto.ProviderType.KAKAO;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOAuthClient {

	private final RestTemplate restTemplate = new RestTemplate();

	private final ObjectMapper objectMapper; // JSON 파싱을 위한 ObjectMapper

	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String kakaoClientId;

	@Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
	private String kakaoRedirectUri;

	@Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
	private String kakaoTokenUri;

	@Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
	private String kakaoUserInfoUri;

	/**
	 * 카카오 인가 코드를 사용하여 액세스 토큰 및 리프레시 토큰을 카카오로부터 발급받습니다.
	 *
	 * @param code 프론트엔드로부터 받은 카카오 인가 코드
	 * @return 카카오의 토큰 응답 (JsonNode)
	 */
	public JsonNode getKakaoTokens(String code) {
		log.info("카카오 토큰 요청 시작, 인가 코드: {}", code);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // 폼 데이터 전송

		// 요청 바디에 포함될 파라미터들
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", kakaoRedirectUri);
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

		try {
			// RestTemplate을 사용하여 POST 요청 전송
			ResponseEntity<String> response = restTemplate.exchange(
				kakaoTokenUri,
				HttpMethod.POST,
				requestEntity,
				String.class
			);

			if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
				log.error("카카오 토큰 발급 실패: HTTP 상태 코드 {} 또는 응답 본문 없음", response.getStatusCode());
				throw new RuntimeException("카카오 토큰 발급에 실패했습니다.");
			}

			// 응답 본문을 JsonNode로 파싱
			JsonNode responseNode = objectMapper.readTree(response.getBody());
			if (!responseNode.has("access_token")) {
				log.error("카카오 토큰 응답에 access_token이 없습니다: {}", response.getBody());
				throw new RuntimeException("카카오 토큰 응답이 유효하지 않습니다.");
			}

			log.info("카카오 액세스 토큰 수신 완료.");
			return responseNode;

		} catch (HttpClientErrorException e) {
			log.error("카카오 토큰 발급 중 HTTP 클라이언트 오류 발생: 상태 코드={}, 응답 본문={}", e.getStatusCode(),
				e.getResponseBodyAsString(), e);
			throw new RuntimeException("카카오 토큰 발급에 실패했습니다. (HTTP 오류)", e);
		} catch (Exception e) {
			log.error("카카오 토큰 발급 중 알 수 없는 오류 발생: {}", e.getMessage(), e);
			throw new RuntimeException("카카오 토큰 발급에 실패했습니다.", e);
		}
	}
}