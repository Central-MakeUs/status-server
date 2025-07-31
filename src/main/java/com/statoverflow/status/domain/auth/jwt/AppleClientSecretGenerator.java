package com.statoverflow.status.domain.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AppleClientSecretGenerator {

	@Value("${status.oauth.apple.client.team-id}")
	private String teamId;

	@Value("${status.oauth.apple.client.key-id}")
	private String keyId;

	@Value("${status.oauth.apple.client.private-key-path}")
	private String privateKeyPath;

	@Value("${status.oauth.apple.client.service-id}")
	private String serviceId;

	private PrivateKey cachedPrivateKey; // PrivateKey 캐싱

	public String generateClientSecret() {
		try {
			if (cachedPrivateKey == null) {
				// .p8 파일 로드 (리소스로부터)
				String privateKeyContent = new String(Files.readAllBytes(
					Paths.get(getClass().getClassLoader().getResource(privateKeyPath.replace("classpath:", "")).toURI())
				))
					.replace("-----BEGIN PRIVATE KEY-----", "")
					.replace("-----END PRIVATE KEY-----", "")
					.replaceAll("\\s", ""); // 공백 제거

				byte[] decodedKey = Base64.getDecoder().decode(privateKeyContent);

				PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
				KeyFactory keyFactory = KeyFactory.getInstance("EC"); // Apple은 ES256 (EC) 사용
				cachedPrivateKey = keyFactory.generatePrivate(keySpec);
				log.info("Apple private key loaded and cached.");
			}

			// JWT 생성
			return Jwts.builder()
				.header().keyId(keyId).and()
				.issuer(teamId)
				.issuedAt(Date.from(Instant.now()))
				.expiration(Date.from(Instant.now().plus(30, ChronoUnit.DAYS))) // 30일
				.audience().add("https://appleid.apple.com").and()
				.subject(serviceId)
				.signWith(cachedPrivateKey)
				.compact();

		} catch (Exception e) { // URISyntaxException, IOException, NoSuchAlgorithmException, InvalidKeySpecException
			log.error("Failed to generate Apple Client Secret: {}", e.getMessage(), e);
			throw new RuntimeException("Error generating Apple Client Secret", e);
		}
	}
}