package com.statoverflow.status.global.jwt;

import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		String token = jwtService.resolveTokenFromCookie(request, "access_token");

		if (StringUtils.hasText(token) && jwtService.validateToken(token)) {
			try {
				BasicUsersDto userDto = jwtService.parseUsersFromToken(token);

				PreAuthenticatedAuthenticationToken authentication =
					new PreAuthenticatedAuthenticationToken(
						userDto,
						null,
						Collections.emptyList() // 권한이 없다면 빈 리스트
					);

				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, URI: {}",
					userDto.id(), request.getRequestURI());

			} catch (Exception e) {
				log.error("JWT 인증 처리 중 오류 발생: {}", e.getMessage(), e);
				SecurityContextHolder.clearContext();
			}
		} else {
			log.debug("유효한 JWT 토큰이 없거나 토큰 검증에 실패했습니다. URI: {}", request.getRequestURI());
		}

		filterChain.doFilter(request, response);
	}


}