package com.hsryuuu.base.java.resttemplate;

import com.hsryuuu.base.java.resttemplate.request.ExtraHeaders;
import com.hsryuuu.base.java.resttemplate.request.QueryParams;
import com.hsryuuu.base.java.resttemplate.response.RestApiResponse;
import com.hsryuuu.base.java.resttemplate.util.RestApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class RestTemplateExecutor {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * execute HTTP GET Request
     *
     * @param url          : base url
     * @param queryParams  : query parameters
     * @param extraHeaders : Http Headers
     * @param data         : Http Body (optional)
     * @return ApiResponse(status, body, errorMessage)
     */
    public RestApiResponse get(String url, QueryParams queryParams, ExtraHeaders extraHeaders, Object data) {
        return this.getResult(HttpMethod.GET, url, queryParams, extraHeaders, data);
    }

    public RestApiResponse get(String url) {
        return this.get(url, null, null, null);
    }

    public RestApiResponse get(String url, ExtraHeaders extraHeaders) {
        return this.get(url, null, extraHeaders, null);
    }

    // POST
    public RestApiResponse post(String url, QueryParams queryParams, ExtraHeaders extraHeaders, Object data) {
        return this.getResult(HttpMethod.POST, url, queryParams, extraHeaders, data);
    }

    public RestApiResponse post(String url, Object body) {
        return this.post(url, null, null, body);
    }

    public RestApiResponse post(String url, ExtraHeaders extraHeaders, Object body) {
        return this.post(url, null, extraHeaders, body);
    }

    // PUT
    public RestApiResponse put(String url, QueryParams queryParams, ExtraHeaders extraHeaders, Object data) {
        return this.getResult(HttpMethod.PUT, url, queryParams, extraHeaders, data);
    }

    public RestApiResponse put(String url, Object body) {
        return this.put(url, null, null, body);
    }

    public RestApiResponse put(String url, ExtraHeaders extraHeaders, Object body) {
        return this.put(url, null, extraHeaders, body);
    }

    // DELETE
    public RestApiResponse delete(String url, QueryParams queryParams, ExtraHeaders extraHeaders, Object data) {
        return this.getResult(HttpMethod.DELETE, url, queryParams, extraHeaders, data);
    }

    public RestApiResponse delete(String url, Object body) {
        return this.delete(url, null, null, null);
    }

    public RestApiResponse delete(String url, ExtraHeaders extraHeaders) {
        return this.delete(url, null, extraHeaders, null);
    }

    /**
     * HTTP Request
     *
     * @param httpMethod      GET / POST / PUT / DELETE
     * @param baseUrl         Request URL
     * @param queryParameters query parameter map
     * @param extraHeaders    http header map
     * @param body            Request Body
     * @return ApiResponse(status, body, errorMessage)
     */
    private RestApiResponse getResult(HttpMethod httpMethod,
                                  String baseUrl,
                                  QueryParams queryParameters,
                                  ExtraHeaders extraHeaders,
                                  Object body) {
        //baseUrl validation
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("Base URL cannot be null or empty value");
        }
        try {
            String url = RestApiUtils.buildFullUrl(baseUrl, queryParameters);
            HttpHeaders headers = RestApiUtils.generateHttpHeaders(extraHeaders);

            log.info("[{}] API Request: URL=[{}], Headers=[{}], Body=[{}]", httpMethod, url, headers, body);
            long startTime = System.currentTimeMillis();

            HttpEntity<Object> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, request, String.class);

            log.info("[{}] API Response: Status=[{}], TimeElapsed={}ms, Body=[{}]",
                    httpMethod, response.getStatusCode(), System.currentTimeMillis() - startTime, response.getBody());
            return new RestApiResponse((HttpStatus) response.getStatusCode(), null, response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return new RestApiResponse((HttpStatus) e.getStatusCode(), e.getResponseBodyAsString(), e.getMessage());
        } catch (RestClientException e) {
            return new RestApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, null, e.getMessage());
        } catch (Exception e) {
            return RestApiResponse.unexpectedError();
        }
    }

}
