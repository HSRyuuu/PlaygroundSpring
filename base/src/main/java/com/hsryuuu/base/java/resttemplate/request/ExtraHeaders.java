package com.hsryuuu.base.java.resttemplate.request;

import com.hsryuuu.base.java.resttemplate.util.RestApiUtils;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * Http Request Header 에 포함될 값들을 감싼 클래스
 * <br> - 설계시, Map data 에는 잘못된 값이 들어갈 수 없도록 한다.
 */
@ToString
@Getter
public class ExtraHeaders {

    Map<String, String> data;

    public ExtraHeaders() {
        this.data = new HashMap<>();
    }

    public ExtraHeaders(Map<String, String> data) {
        if(data == null){
            this.data = new HashMap<>();
            return;
        }
        for(Map.Entry<String, String> entry : data.entrySet()){
            this.add(entry.getKey(), entry.getValue());
        }
    }

    public ExtraHeaders add(String key, String value){
        if(!RestApiUtils.validateKeyValue(key, value)){
            return this;
        }
        this.data.put(key, value);
        return this;
    }
}
