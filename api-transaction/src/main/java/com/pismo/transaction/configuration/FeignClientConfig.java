package com.pismo.transaction.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.header("Accept", "application/json");
        };
    }

}