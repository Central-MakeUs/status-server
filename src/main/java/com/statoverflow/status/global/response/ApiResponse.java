package com.statoverflow.status.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.statoverflow.status.domain.users.dto.BasicUsersDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.statoverflow.status.global.error.ErrorType;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@Builder(access = lombok.AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;

    // OK 응답 생성 메서드
    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<T>builder()
                        .status(HttpStatus.OK.value())
                        .data(data)
                        .build());
    }

    // CREATED 응답 생성 메서드
    public static <T> ResponseEntity<ApiResponse<T>> created() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<T>builder()
                        .status(HttpStatus.CREATED.value())
                        .build());
    }

    // CREATED 응답 생성 메서드
    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<T>builder()
                        .status(HttpStatus.CREATED.value())
                        .data(data)
                        .build());
    }

    public static  ResponseEntity<ApiResponse<?>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.builder()
                        .status(HttpStatus.NO_CONTENT.value())
                        .build());
    }

    // 에러 응답 생성 메서드
    public static ResponseEntity<ApiResponse<Void>> error(ErrorType errorType) {
        return ResponseEntity.status(errorType.getStatus())
                .body(ApiResponse.<Void>builder()
                        .status(errorType.getStatus().value())
                        .code(errorType.getErrorCode())
                        .message(errorType.getMessage())
                        .build());
    }


    // 에러 응답 생성 메서드
    public static ResponseEntity<ApiResponse<Void>> error(ErrorType errorType, String msg) {
        return ResponseEntity.status(errorType.getStatus())
            .body(ApiResponse.<Void>builder()
                .status(errorType.getStatus().value())
                .code(errorType.getErrorCode())
                .message(errorType.getMessage()+msg)
                .build());
    }

}