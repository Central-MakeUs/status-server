package com.statoverflow.status.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    // global 에러 (00)
    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "00-001", "현재 앱에 문제가 발생했으니 관리자에게 문의해주세요."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "00-002", "요청한 리소스를 찾을 수 없습니다."),

    // 소셜 로그인 에러 (01)
    UNSUPPORTED_OAUTH_PROVIDER(HttpStatus.BAD_REQUEST, "01-001", "잘못된 소셜 식별자입니다."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "01-002", "해당 소셜 계정으로 가입된 사용자를 찾을 수 없습니다."),

    NICKNAME_NOT_CHANGED(HttpStatus.CONFLICT, "02-001", "닉네임이 이전과 동일합니다.")



    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
    }