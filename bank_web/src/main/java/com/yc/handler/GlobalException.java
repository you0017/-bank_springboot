package com.yc.handler;

import com.yc.bean.JsonModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public JsonModel exceptionHandler(RuntimeException e) {
        e.printStackTrace();
        JsonModel jm = new JsonModel();
        jm.setCode(0);
        jm.setError(e.getMessage());
        return jm;
    }
}
