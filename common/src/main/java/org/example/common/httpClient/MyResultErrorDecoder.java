package org.example.common.httpClient;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyResultErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status()!=200){
            log.error("invalid http response status: {}",response.status());
            throw new IllegalArgumentException("invalid http response status");
        }
        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}
