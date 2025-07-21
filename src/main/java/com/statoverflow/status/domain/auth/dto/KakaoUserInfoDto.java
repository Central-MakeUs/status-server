package com.statoverflow.status.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoDto {
	private Long id; // 사용자 고유 ID

	// 다른 필드는 무시
}