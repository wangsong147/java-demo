package com.example.javamaildemo.handler;

import com.example.javamaildemo.utils.ResultMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcetionHandler {

    @ExceptionHandler(Exception.class)
    public ResultMessage handleLoginException(RuntimeException e)
    {
        return ResultMessage.error(5000,e.getMessage());
    }

}
