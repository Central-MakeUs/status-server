package com.statoverflow.status.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.addServersItem(new Server().url("/"))
			.info(new Info()
				.title("상태창! API")
				.description("Status Service API Docs")
				.version("v1.0.0"));
	}

}