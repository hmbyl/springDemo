package org.springDemo.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springDemo.common.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleException(Exception e) {
        Result result = new Result();
        result.setCode(500);
        result.setMsg(e.getMessage());
        log.error(e.getMessage(), e);
        return result;
    }
}
