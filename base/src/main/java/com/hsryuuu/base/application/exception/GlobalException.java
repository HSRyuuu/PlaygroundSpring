package com.hsryuuu.base.application.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@Setter
public class GlobalException extends RuntimeException {
    private HttpStatus status;
    private String errorMessage;
    private Throwable cause;
    private Object data;

    public GlobalException(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.errorMessage = errorCode.getMessage();
    }

    public GlobalException(ErrorCode errorCode, Throwable cause, Object data) {
        this.status = errorCode.getStatus();
        this.errorMessage = errorCode.getMessage();
        this.cause = cause;
        this.data = data;
    }


    public static GlobalException defaultNotFound(String additionalInfo) {
        String errorMessage = ErrorCode.NOT_FOUND.getMessage() + "=>" + additionalInfo;
        return new GlobalException(HttpStatus.NOT_FOUND, errorMessage, null, null);
    }

}
