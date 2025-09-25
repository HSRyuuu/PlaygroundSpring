package com.hsryuuu.base.java.resttemplate.request;

import com.hsryuuu.base.java.resttemplate.util.RestApiUtils;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.ToString;

/**
 * Http Request Url 에 포함될 query parameter 값들을 감싼 클래스
 * <br> - 설계시, Map data 에는 잘못된 값이 들어갈 수 없도록 한다.
 */
@ToString
@Getter
public class QueryParams {

    private Map<String, String> data;

    public QueryParams() {
        this.data = new HashMap<>();
    }

    public QueryParams(Map<String, String> data) {
        if(data == null){
            this.data = new HashMap<>();
            return;
        }
        for(Map.Entry<String, String> entry : data.entrySet()){
            this.add(entry.getKey(), entry.getValue());
        }
    }

    public QueryParams add(String key, String value) {
        if(!RestApiUtils.validateKeyValue(key, value)){
            return this;
        }
        this.data.put(key, value);
        return this;
    }

    public String getQueryParameterString(){
        return "";
    }
}
