package org.example.common.httpClient;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "org.example.common.httpClient")
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignInterceptor() ;
    }

    @Bean
    public Decoder feignDecoder() {
        return new MyResultDecoder();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new MyResultErrorDecoder();
    }
}
