package com.hsryuuu.base.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    // # 공통
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST 잘못된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND 존재하지 않는 데이터입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR 서버측 알수 없는 에러입니다."),
    BAD_REQUEST_ENUM(HttpStatus.BAD_REQUEST, "잘못된 enum 값 요청입니다."),
    DATA_INVALID_EMPTY_VALUE(HttpStatus.BAD_REQUEST, "필수값이 빠져있습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다.");

    private final HttpStatus status;
    private final String message;
}
