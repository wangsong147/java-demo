package com.example.javamaildemo.concurrent.create_thread.callable;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class MyCallable1 implements Callable<String> {

    @Override
    public String call() throws Exception {
        log.info( Thread.currentThread().getName() + "::call");
        return "call";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable1 myCallable1 = new MyCallable1();
        FutureTask<String> task = new FutureTask<>(myCallable1);

        Thread thread = new Thread(task, "AA");
        thread.start();

        String s = task.get();
        System.out.println(s);
    }

}
