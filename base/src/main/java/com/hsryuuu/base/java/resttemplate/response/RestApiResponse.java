package com.hsryuuu.base.java.resttemplate.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class RestApiResponse {
    private HttpStatus status;
    private String errorMessage;
    private Object body;

    public static RestApiResponse unexpectedError(){
        return new RestApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "unexpected error",null);
    }

}
