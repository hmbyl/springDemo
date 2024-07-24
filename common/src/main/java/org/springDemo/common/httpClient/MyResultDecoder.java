package org.springDemo.common.httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springDemo.common.entity.Result;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.lang.reflect.Type;

public class MyResultDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        if(response.status()!=200){
            throw new HttpServerErrorException( HttpStatusCode.valueOf(response.status()));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Result result= objectMapper.readValue(response.body().asReader(), Result.class);

        return result.getData();
    }
}
