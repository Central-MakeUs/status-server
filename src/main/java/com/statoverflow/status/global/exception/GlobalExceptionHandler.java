package com.statoverflow.status.global.exception;

import com.statoverflow.status.domain.auth.dto.OAuthProviderDto;
import com.statoverflow.status.global.error.ErrorType;
import com.statoverflow.status.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException ex) {
        log.error("CustomException: {}", ex.getMessage(), ex);

        Object data = ex.getData();

        // 데이터가 있으면 데이터와 함께 응답, 없으면 일반 에러 응답
        if (data != null) {
            return ApiResponse.errorWithData(ex.getErrorType(), data);
        } else {
            return ApiResponse.errorWithObject(ex.getErrorType());
        }
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.warn("NoResourceFoundException: {}", ex.getMessage(), ex);
        return ApiResponse.error(ErrorType.RESOURCE_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception ex) {
        log.error("Exception: {}", ex.getMessage(), ex);
        return ApiResponse.error(ErrorType.DEFAULT_ERROR);
    }

}