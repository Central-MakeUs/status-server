package com.statoverflow.status.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys; // Keys 임포트
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Authentication 관련 클래스 임포트
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User; // Spring Security UserDetails의 기본 구현체
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key; // Key 임포트
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
@Component
public class JwtTokenProvider { // 클래스명 변경 권장 (JwtProvider -> JwtTokenProvider)

    @Value("${jwt.secret}")
    private String secret; // application.yml의 secret 값 주입

    // 만료 시간은 application.yml에서 직접 주입받는 것이 더 유연합니다.
    @Value("${jwt.access-token-expiration-ms}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refresh-token-expiration-ms}")
    private long refreshTokenExpirationMs;

    private SecretKey secretKey;


    // 생성자 주입 대신 @PostConstruct를 사용하여 secretKey가 주입된 후 Key를 초기화
    @PostConstruct
    public void init() {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
            Jwts.SIG.HS256.key().build().getAlgorithm())
        ;
    }

    // Access Token 생성 (Authentication 객체를 받도록 변경하여 권한 정보 포함)
    public String generateAccessToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpirationMs);

        return Jwts.builder()
            .setSubject(authentication.getName()) // 사용자 ID (principal의 이름)
            .claim("auth", authorities) // 권한 정보를 "auth" 클레임에 추가
            .setIssuedAt(now) // 발행 시간
            .setExpiration(validity) // 만료 시간
            .signWith(key, SignatureAlgorithm.HS256) // Key 객체를 사용하여 서명
            .compact();
    }

    // Refresh Token 생성 (JTI(JWT ID) 클레임을 포함하여 고유성 보장)
    public String generateRefreshToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenExpirationMs);

        return Jwts.builder()
            .setSubject(authentication.getName()) // 사용자 ID
            .claim("jti", UUID.randomUUID().toString()) // Refresh Token의 고유 식별자
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    // JWT에서 인증 정보 추출 (Spring Security의 Authentication 객체 반환)
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser() // parserBuilder() 사용
            .setSigningKey(key) // Key 객체 사용
            .build()
            .parseClaimsJws(token)
            .getBody();

        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get("auth").toString().split(",")) // "auth" 클레임에서 권한 추출
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails의 기본 구현체인 User 사용
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    // 토큰 유효성 검증 (더 상세한 예외 처리)
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(SecretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    // JWT에서 사용자 ID 추출 (subject 클레임)
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    // Refresh Token에서 JTI(JWT ID) 추출
    public String getJtiFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("jti", String.class); // "jti" 클레임 추출
    }

    // 토큰 만료 시간 추출
    public Date getExpirationDateFromToken(String token) {
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
    }
}