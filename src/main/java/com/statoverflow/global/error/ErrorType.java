package com.statoverflow.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    // global 에러 (00)
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "00-001", "현재 앱에 문제가 발생했으니 관리자에게 문의해주세요."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "00-002", "요청한 리소스를 찾을 수 없습니다."),

    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
    }