package com.example.javamaildemo.concurrent.create_thread.callable;

import java.util.concurrent.Callable;

public class MyCallable1 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "return string";
    }

}