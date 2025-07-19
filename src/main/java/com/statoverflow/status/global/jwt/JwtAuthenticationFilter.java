package com.statoverflow.status.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor // Lombok을 사용하여 final 필드를 인자로 받는 생성자 자동 생성
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// Access Token을 저장할 쿠키의 이름
	public static final String ACCESS_TOKEN_COOKIE_NAME = "access_token";

	private final JwtTokenProvider jwtTokenProvider; // JWT 토큰을 제공하는 서비스 주입

	/**
	 * 모든 HTTP 요청에 대해 필터링 로직을 수행합니다.
	 *
	 * @param request  HTTP 요청 객체
	 * @param response HTTP 응답 객체
	 * @param filterChain 필터 체인
	 * @throws ServletException 서블릿 예외
	 * @throws IOException      입출력 예외
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		// 1. Request Cookie에서 Access Token 추출
		String jwt = resolveTokenFromCookie(request);

		// 2. 추출된 토큰이 있고 유효한지 검증
		if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
			// 토큰이 유효할 경우, 토큰에서 Authentication 객체를 가져와 SecurityContext에 저장
			Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, URI: {}", authentication.getName(), request.getRequestURI());
		} else {
			// 유효한 토큰이 없거나 토큰 검증에 실패한 경우
			log.debug("유효한 JWT 토큰이 없습니다. 또는 토큰 검증에 실패했습니다. URI: {}", request.getRequestURI());
		}

		// 다음 필터 또는 서블릿으로 요청 전달
		filterChain.doFilter(request, response);
	}

	/**
	 * HttpServletRequest의 쿠키에서 Access Token을 추출합니다.
	 *
	 * @param request HTTP 요청 객체
	 * @return Access Token 문자열 (없으면 null)
	 */
	private String resolveTokenFromCookie(HttpServletRequest request) {
		// 요청에 쿠키가 없는 경우 null 반환
		if (request.getCookies() == null) {
			return null;
		}

		// 쿠키 배열에서 "access_token" 이름의 쿠키를 찾아 값 반환
		Optional<Cookie> accessTokenCookie = Arrays.stream(request.getCookies())
			.filter(cookie -> ACCESS_TOKEN_COOKIE_NAME.equals(cookie.getName()))
			.findFirst();

		return accessTokenCookie.map(Cookie::getValue).orElse(null);
	}
}