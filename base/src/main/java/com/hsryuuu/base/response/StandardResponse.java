package com.hsryuuu.base.response;

import com.hsryuuu.base.type.OperationResult;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardResponse<T> {
    @Builder.Default
    private OperationResult result = OperationResult.SUCCESS;
    private int statusCode;
    private String message;
    private T data;

    //에러
    public StandardResponse(HttpStatus status) {
        this(OperationResult.ERROR, status.value(), status.getReasonPhrase(), null);
    }

    public StandardResponse(HttpStatus status, String message) {
        this(OperationResult.ERROR, status.value(), message, null);
    }

    public StandardResponse(HttpStatus status, T data) {
        this(OperationResult.ERROR, status.value(), status.getReasonPhrase(), data);
    }

    public StandardResponse(HttpStatus status, String message, T data) {
        this(OperationResult.ERROR, status.value(), message, data);
    }

    public StandardResponse(Exception e) {
        this(OperationResult.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
    }

    //성공 응답
    public StandardResponse(T data) {
        this(OperationResult.SUCCESS, HttpStatus.OK.value(), "", data);
    }

    /**
     * default 성공 응답
     */
    public static <T> StandardResponse<T> defaultSuccess() {
        return new StandardResponse<>(OperationResult.SUCCESS, HttpStatus.OK.value(), "", null);
    }

    /**
     * default 성공 응답
     */
    public static <T> StandardResponse<T> defaultSuccess(T data) {
        return new StandardResponse<>(OperationResult.SUCCESS, HttpStatus.OK.value(), "", data);
    }


}
