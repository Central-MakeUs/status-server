package com.statoverflow.status.domain.auth.util;

import com.statoverflow.status.domain.auth.dto.GoogleTokenResponseDto;
import com.statoverflow.status.domain.auth.dto.GoogleUserInfoDto;
import com.statoverflow.status.domain.auth.dto.KakaoTokenResponseDto;
import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.domain.auth.dto.OAuthUserInfoDto;
import com.statoverflow.status.domain.users.enums.ProviderType;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.exception.CustomException;

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

import java.util.Collections;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GoogleOAuthClient {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String googleTokenEndpoint;

    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String googleUserInfoEndpoint;


    // todo: WebClient로 통신
    private final RestTemplate restTemplate = new RestTemplate();

    public GoogleTokenResponseDto getGoogleTokens(String code) {
        log.info("구글 토큰 요청 시작, 인가 코드: {}", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 요청 바디에 포함될 파라미터들
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", googleClientId);
        params.add("client_secret", googleClientSecret);
        params.add("code", code);
        params.add("redirect_uri", googleRedirectUri);
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ParameterizedTypeReference<GoogleTokenResponseDto> responseType = new ParameterizedTypeReference<>() {
        };


        try {

            ResponseEntity<GoogleTokenResponseDto> response = restTemplate.exchange(
                googleTokenEndpoint,
                HttpMethod.POST,
                request,
                responseType
            );

            log.info("구글 액세스 토큰 수신 완료.");
            return response.getBody();

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(ErrorType.DEFAULT_ERROR);
        }
    }

    public String getUserInfo(GoogleTokenResponseDto tokenResponseDto) {
        log.info("구글 사용자 정보 요청 시작, 엑세스 토큰: {}", tokenResponseDto.getAccessToken());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenResponseDto.getAccessToken());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        ParameterizedTypeReference<GoogleUserInfoDto> responseType = new ParameterizedTypeReference<>() {
        };


        try {

            ResponseEntity<GoogleUserInfoDto> response = restTemplate.exchange(
                googleUserInfoEndpoint,
                HttpMethod.GET,
                request,
                responseType
            );

            log.info("구글 사용자 정보 수신 완료: {}", response.getBody());
            return response.getBody().sub();

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(ErrorType.DEFAULT_ERROR);
        }
    }
}
