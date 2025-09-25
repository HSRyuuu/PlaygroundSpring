package com.hsryuuu.base.java.resttemplate.util;


import com.hsryuuu.base.java.resttemplate.request.ExtraHeaders;
import com.hsryuuu.base.java.resttemplate.request.QueryParams;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public class RestApiUtils {

    /**
     * HTTP Header 객체 생성
     * @param extraHeaders ExtraHeaders Object
     * @return
     */
    public static HttpHeaders generateHttpHeaders(ExtraHeaders extraHeaders) {
        if (extraHeaders == null || extraHeaders.getData() == null || extraHeaders.getData().isEmpty()) {
            return new HttpHeaders();
        }

        HttpHeaders headers = new HttpHeaders();
        for (Map.Entry<String, String> entry : extraHeaders.getData().entrySet()) {
            headers.add(entry.getKey(), entry.getValue());
        }
        return headers;
    }

    /**
     * 전체 URL 생성 (base url + query params)
     * @param queryParams QueryParams Object
     * @return full url with query parameters
     */
    public static String buildFullUrl(String baseUrl, QueryParams queryParams){
        if(baseUrl == null || baseUrl.isEmpty()){
            throw new IllegalArgumentException("Base Url must not be null or empty");
        }
        if(queryParams == null || queryParams.getData() == null || queryParams.getData().isEmpty()){
            return baseUrl;
        }
        //make queryParameter String
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        for (Map.Entry<String, String> entry : queryParams.getData().entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
        String queryParameterString = builder.build().toString();

        if(baseUrl.contains("?")){
            return baseUrl + queryParameterString.replaceAll("\\?", "&");
        }

        return baseUrl + queryParameterString;
    }

    public static boolean validateKeyValue(String key, String value){
        if(key == null || key.isEmpty()){
            return false;
        }
        if( value == null || value.isEmpty()) {
            return false;
        }
        return true;
    }

}
