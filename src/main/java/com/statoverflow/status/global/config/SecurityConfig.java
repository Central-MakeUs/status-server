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
import com.statoverflow.status.global.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final CorsConfig corsConfig;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

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
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}