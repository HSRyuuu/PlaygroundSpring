package com.hsryuuu.base.java.resttemplate;


import com.hsryuuu.base.java.resttemplate.request.ExtraHeaders;
import com.hsryuuu.base.java.resttemplate.request.QueryParams;
import com.hsryuuu.base.java.resttemplate.response.RestApiResponse;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class RestTemplateExecutorTest {

    private final RestTemplateExecutor restApiExecutor = new RestTemplateExecutor();
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @Nested
    @DisplayName("GET")
    class GetTest{

        @Test
        @DisplayName("(1) pure get")
        void getTest1(){
            String path = "/posts/1";
            RestApiResponse response = restApiExecutor.get(BASE_URL + path);
            System.out.println(" ### RESPONSE ### ");
            System.out.println(response);
        }

        @Test
        @DisplayName("(2) with query Params")
        void getTest2(){
            String path = "/comments";
            QueryParams queryParams = new QueryParams().add("postId", "1");
            ExtraHeaders headers = new ExtraHeaders()
                    .add("Content-Type", "application/json")
                    .add("access_token", "123123");
            RestApiResponse response = restApiExecutor.get(BASE_URL + path, queryParams, headers, null);
            System.out.println(" ### RESPONSE ### ");
            System.out.println(response);
        }
    }

    @Nested
    @DisplayName("POST")
    class PostTest{

        @Test
        @DisplayName("(1) post")
        void postTest1(){
            String path = "/posts";
            Map<String, String> bodyJson = new HashMap<>();
            bodyJson.put("userId", "1");
            bodyJson.put("id", "1");
            bodyJson.put("title", "테스트 제목");
            bodyJson.put("body", "테스트 내용. This posts body for test.");

            RestApiResponse response = restApiExecutor.post(BASE_URL + path, bodyJson);
            System.out.println(" ### RESPONSE ### ");
            System.out.println(response);

        }

    }

    @Nested
    @DisplayName("PUT")
    class PutTest{

        @Test
        @DisplayName("(1) put")
        void postTest1(){
            String path = "/posts/1";
            Map<String, String> bodyJson = new HashMap<>();
            bodyJson.put("userId", "1");
            bodyJson.put("id", "1");
            bodyJson.put("title", "제목 수정");
            bodyJson.put("body", "내용 수정. This posts body for test.");

            ExtraHeaders headers = new ExtraHeaders()
                    .add("Content-Type", "application/json")
                    .add("access_token", "test-123-123");

            RestApiResponse response = restApiExecutor.put(BASE_URL + path, headers, bodyJson);
            System.out.println(" ### RESPONSE ### ");
            System.out.println(response);

        }

    }

    @Nested
    @DisplayName("DELETE")
    class DeleteTest{

        @Test
        @DisplayName("(1) delete")
        void postTest1(){
            String path = "/posts/1";

            ExtraHeaders headers = new ExtraHeaders()
                    .add("Content-Type", "application/json")
                    .add("access_token", "test-123-123");

            RestApiResponse response = restApiExecutor.delete(BASE_URL + path, headers);
            System.out.println(" ### RESPONSE ### ");
            System.out.println(response);
        }
    }

}