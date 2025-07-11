package com.statoverflow.status.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CorsConfig corsConfig;

	//AuthenticationManager Bean 등록
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager,
		JwtFilter jwtFilter,
		AuthService authService, OauthService oauthService) throws
		Exception {

		//http basic 인증 방식 disable
		http
			.cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
			.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(authorize ->
				authorize
					.requestMatchers(
						"/api/v1/auth/**",
						"api/v1/health",
						"/api/v1/members/checkNickname",
						"/oauth2/**",
						"/**"
					)
					.permitAll()
					.anyRequest().authenticated()
			)
			.oauth2Login(oauth2 -> oauth2
				.authorizationEndpoint(authorization -> authorization
					.baseUri("/oauth2/authorize/**")
				)
				.redirectionEndpoint(redirection -> redirection
					.baseUri("/oauth2/login/code/*"))
				.userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
					.userService(oauthService))
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
			)

			// OAuth 로그인 설정
			.oauth2Login(customConfigurer -> customConfigurer
				.successHandler(successHandler)
				.failureHandler(failureHandler)
				.userInfoEndpoint(endpointConfig -> endpointConfig.userService(customOAuthService))
			);

			.addFilter(new LoginFilter(authenticationManager, jwtUtil, tokenService))
			// JwtFilter 등록
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			// 세션 설정
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(exceptionHandling ->
				exceptionHandling
					.accessDeniedHandler(accessDeniedHandler)
					.authenticationEntryPoint(authenticationEntryPoint))
			// 로그아웃
			.logout(logout -> logout
				.logoutUrl("/api/v1/auth/logout")  // 프론트에서 POST 요청할 경로
				.addLogoutHandler(customLogoutHandler)
				.logoutSuccessHandler((request, response, authentication) -> {
					response.setStatus(HttpServletResponse.SC_OK);
				})
			);

		return http.build();
	}
}