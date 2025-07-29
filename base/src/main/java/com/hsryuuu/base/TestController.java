package com.hsryuuu.base;

import com.hsryuuu.base.exception.ErrorCode;
import com.hsryuuu.base.exception.GlobalException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public TestRecord test(){
        return new TestRecord(10, "Hello");
    }

    public record TestRecord(int age, String name) {
    }

    @GetMapping("/error")
    public String error(){
        throw new GlobalException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
