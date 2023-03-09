package com.example.javamaildemo.handler;

import com.example.javamaildemo.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcetionHandler {

    @ExceptionHandler(Exception.class)
    public R handleLoginException(RuntimeException e)
    {
        return R.error(5000,e.getMessage());
    }

}
