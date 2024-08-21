package com.yc.handler;

import com.yc.bean.JsonModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public JsonModel exceptionHandler(Exception e) {
        JsonModel jm = new JsonModel();
        jm.setError(e.getMessage());
        return jm;
    }
}
