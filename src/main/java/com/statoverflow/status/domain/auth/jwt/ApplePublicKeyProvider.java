package com.statoverflow.status.domain.auth.jwt;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.statoverflow.status.domain.auth.dto.ApplePublicKeyResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplePublicKeyProvider {

	private static final String APPLE_JWKS_URI = "https://appleid.apple.com/auth/keys";

	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	// kid를 키로, PublicKey를 값으로 캐싱
	private final Map<String, PublicKey> cachedPublicKeys = new ConcurrentHashMap<>();

	public PublicKey getPublicKey(String kid) {
		// 캐시에서 먼저 찾기
		if (cachedPublicKeys.containsKey(kid)) {
			return cachedPublicKeys.get(kid);
		}

		// 캐시에 없으면 Apple JWKS에서 가져오기
		log.info("Apple public key for kid {} not found in cache. Fetching from Apple...", kid);
		ApplePublicKeyResponseDto response = restTemplate.getForObject(APPLE_JWKS_URI, ApplePublicKeyResponseDto.class);

		if (response != null && response.keys() != null) {
			for (ApplePublicKeyResponseDto.AppleKey key : response.keys()) {
				if (kid.equals(key.kid())) {
					try {
						PublicKey newKey = createPublicKey(key.n(), key.e());
						cachedPublicKeys.put(kid, newKey); // 캐시에 저장
						log.info("Apple public key for kid {} fetched and cached.", kid);
						return newKey;
					} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
						log.error("Failed to create PublicKey for kid {}: {}", kid, e.getMessage(), e);
					}
				}
			}
		}

		log.warn("Apple public key for kid {} not found.", kid);
		return null;
	}

	private PublicKey createPublicKey(String n, String e) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] decodedN = Base64.getUrlDecoder().decode(n);
		byte[] decodedE = Base64.getUrlDecoder().decode(e);

		BigInteger modulus = new BigInteger(1, decodedN);
		BigInteger exponent = new BigInteger(1, decodedE);

		RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, exponent);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(publicKeySpec);
	}
}