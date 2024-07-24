package org.example.common.httpClient;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("auth","123");
        requestTemplate.header("Content-Type","application/json");
    }
}
