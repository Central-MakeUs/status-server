package com.statoverflow.status.domain.auth.util;

import com.statoverflow.status.domain.auth.dto.OAuthUserInfoDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.statoverflow.status.domain.auth.dto.OAuthUserInfoDto.ProviderType.GOOGLE;

@Component
public class GoogleOAuthClient {

    // todo: WebClient로 통신
    private final RestTemplate restTemplate = new RestTemplate();

    public OAuthUserInfoDto getUserId(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "url", // todo: 요청 url 입력
                HttpMethod.GET,
                entity,
                Map.class
        );

        // todo: 에러 타입 맞추기
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Google access token invalid");
        }

        Map<String, String> returnValue = response.getBody();

        return new OAuthUserInfoDto(new OAuthUserInfoDto.OAuthProvider(returnValue.get("id"), GOOGLE)
                , returnValue.get("email"));
    }

}
