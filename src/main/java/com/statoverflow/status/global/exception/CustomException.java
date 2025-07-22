package com.statoverflow.status.global.exception;

import com.statoverflow.status.global.error.ErrorType;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorType errorType;
    private Object data;

    public CustomException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = null;
    }

    public CustomException(ErrorType errorType, Object data) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = data;
    }
}