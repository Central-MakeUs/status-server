package com.statoverflow.status;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class StatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatusApplication.class, args);
	}

	@Bean
	public Random random() {
		return new Random(); // 새 Random 인스턴스 생성 및 반환
	}
}
