package com.statoverflow.status.domain.master.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TermsType {
	SERVICE_TERMS("서비스 이용 약관"), PRIVATE_POLICY("개인정보 처리 방침");

	private final String title;
}
