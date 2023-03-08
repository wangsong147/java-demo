package com.example.javamaildemo.handler;

import com.example.javamaildemo.utils.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExcetionHandler {

    @ExceptionHandler(RuntimeException.class)
    public R handleLoginExcpetion(RuntimeException e)
    {
        return R.error(500,e.getMessage());
    }

}
