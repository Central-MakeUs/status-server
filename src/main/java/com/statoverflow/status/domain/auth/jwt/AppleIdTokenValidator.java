package com.statoverflow.status.domain.auth.jwt;

import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppleIdTokenValidator {

	private final ApplePublicKeyProvider applePublicKeyProvider;

	@Value("${status.oauth.apple.client.service-id}")
	private String serviceId;


	public Claims validateAppleIdTokenAndGetClaims(String idToken) {

		// 1. ID 토큰 헤더에서 kid (Key ID) 가져오기
		String header = idToken.split("\\.")[0];
		String decodedHeader = new String(Base64.getUrlDecoder().decode(header));
		Map<String, String> headerMap = new Gson().fromJson(decodedHeader, Map.class);
		String kid = headerMap.get("kid");

		// 2. Apple 공개키 가져오기
		PublicKey publicKey = applePublicKeyProvider.getPublicKey(kid);
		if (publicKey == null) {
			throw new RuntimeException("Apple Public Key not found for kid: " + kid);
		}

		// 3. ID 토큰 검증 및 파싱
		return Jwts.parser()
			.verifyWith(publicKey)  // 검증용 공개키 설정
			.requireIssuer("https://appleid.apple.com")  // 발급자 검증
			.requireAudience(serviceId)  // 대상 검증
			.build().parseSignedClaims(idToken).getPayload();
	}


}
