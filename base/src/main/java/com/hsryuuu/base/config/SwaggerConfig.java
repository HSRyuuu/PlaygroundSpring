package com.hsryuuu.base.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI flashSaleOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringBootBASE API")
                        .version("v1")
                        .description("SpringBootBASE API Documentation"));
    }
}