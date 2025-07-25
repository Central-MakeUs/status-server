package com.statoverflow.status.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import com.statoverflow.status.domain.users.dto.BasicUsersDto;

@Slf4j
@Component
public class JwtService { // 클래스명 변경 권장 (JwtProvider -> JwtTokenProvider)

    @Value("${jwt.secret}")
    private String secret; // application.yml의 secret 값 주입

    // 만료 시간은 application.yml에서 직접 주입받는 것이 더 유연합니다.
    @Value("${jwt.access-token-expiration-ms}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refresh-token-expiration-ms}")
    private long refreshTokenExpirationMs;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // Access Token 생성
    public String generateAccessToken(BasicUsersDto user) {

        Date now = new Date(System.currentTimeMillis());
        Date validity = new Date(now.getTime() + accessTokenExpirationMs);

        return Jwts.builder()
            .claim("id", user.id())
            .claim("nickname", user.nickname())
            .issuedAt(now)
            .expiration(validity)
            .signWith(secretKey)
            .compact();
    }

    // Refresh Token 생성
    public String generateRefreshToken(BasicUsersDto user) {

        Date now = new Date(System.currentTimeMillis());
        Date validity = new Date(now.getTime() + refreshTokenExpirationMs);

        return Jwts.builder()
            .claim("id", user.id())
            .claim("nickname", user.nickname())
            .issuedAt(now)
            .expiration(validity)
            .signWith(secretKey)
            .compact();
    }

    public void deleteCookie(HttpServletResponse response, String name) {
        ResponseCookie cookie = ResponseCookie.from(name, null)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .sameSite("Lax")
            .maxAge(0)
            .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    // 토큰 유효성 검증 (더 상세한 예외 처리)
    public boolean validateToken(String token) {
        try {
            log.info("Validating token {}", token);
            Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(token);
            log.info("Token validated");
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

    public BasicUsersDto parseUsersFromToken(String token) {

        Claims claims = Jwts.parser()
            .verifyWith(secretKey) // 또는 setSigningKey()
            .build()
            .parseSignedClaims(token)
            .getPayload();

        int id = claims.get("id", Integer.class);
        String nickname = claims.get("nickname", String.class);

        return new BasicUsersDto((long) id, nickname);

    }

    public String resolveTokenFromCookie(HttpServletRequest request, String tokenName) {
        if (request.getCookies() == null) {
            return null;
        }

        return Arrays.stream(request.getCookies())
            .filter(cookie -> tokenName.equals(cookie.getName()))
            .findFirst()
            .map(Cookie::getValue)
            .orElse(null);
    }

    public long getAccessTokenValidityInSeconds() {
        return 60 * 60;
    }

    public long getRefreshTokenValidityInSeconds() {
        return 60 * 60 * 24 * 14;
    }
}