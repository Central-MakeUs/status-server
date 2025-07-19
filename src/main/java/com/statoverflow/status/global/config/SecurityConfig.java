package com.statoverflow.status.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.statoverflow.status.global.jwt.JwtAuthenticationFilter;
import com.statoverflow.status.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CorsConfig corsConfig;
	private final JwtTokenProvider jwtTokenProvider;

	//AuthenticationManager Bean 등록
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws
		Exception {

		//http basic 인증 방식 disable
		http
			.cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

			.authorizeHttpRequests(authorize ->
				authorize
					.requestMatchers(
						"/api/v1/auth/**",
						"/api/v1/health",
						"/oauth2/**",
						"/**"
					)
					.permitAll()
					.anyRequest().authenticated()
			)          // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 이전에 추가
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)

			// 로그아웃 설정
			// .logout(logout -> logout
			// 	.logoutUrl("/api/v1/auth/logout") // 로그아웃 처리할 URL
			// 	.addLogoutHandler(logoutService) // 로그아웃 핸들러 추가 (Redis에서 Refresh Token 삭제 등)
			// 	.logoutSuccessHandler(logoutSuccessHandler) // 로그아웃 성공 시 처리 (HTTP 상태 코드, 메시지 등)
			// 	.deleteCookies("access_token", "refresh_token_id") // 로그아웃 시 쿠키 삭제
			// )
		;

		return http.build();
	}
}