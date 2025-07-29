package com.hsryuuu.base.exception;

import com.hsryuuu.base.response.StandardResponse;
import com.hsryuuu.base.type.OperationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalCustomExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<StandardResponse<Object>> handleBaseException(GlobalException e) {
        StandardResponse<Object> standardResponse = new StandardResponse<>(e.getStatus(), e.getErrorMessage());
        // 발생한 에러가 BaseException.cause 에 담겨있는 경우
        if (e.getCause() != null) {
            standardResponse.setMessage(e.getCause().getMessage());
        }
        if (e.getData() != null) {
            standardResponse.setData(e.getData());
        }
        log.error("Error Message: {}", e.getErrorMessage(), e);
        return ResponseEntity.status(e.getStatus()).body(standardResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ValidationError> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> new ValidationError(err.getField(), err.getDefaultMessage()))
                .toList();

        StandardResponse<Object> standardResponse = StandardResponse.builder()
                .result(OperationResult.ERROR)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("요청 데이터 검증 실패")
                .data(errors)
                .build();

        log.error("MethodArgumentNotValidException: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse<Object>> handleUnknownException(Exception e) {
        StandardResponse<Object> standardResponse = new StandardResponse<>(e);
        log.error("Unknown Error: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardResponse);
    }
}
