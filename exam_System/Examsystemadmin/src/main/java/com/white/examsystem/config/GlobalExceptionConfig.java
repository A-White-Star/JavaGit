package com.white.examsystem.config;

import com.white.examsystem.common.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionConfig {
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e){
        if(e instanceof NoHandlerFoundException){
            return RespBean.fail(404,"NOT FOUND", e).toString();
        }else {
            return RespBean.fail(-9999,"服务器发生未知错误，请联系管理员", e).toString();
        }
    }
}
