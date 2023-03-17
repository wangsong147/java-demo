package com.example.javamaildemo.concurrent;

import com.example.javamaildemo.concurrent.runnable.NumberThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPollTest {

    public static void main(String[] args) {
        // 造了10个线程，至于做什么要自定义
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 指定自己要什么事情
        executorService.execute(new NumberThread());// 多用于runnabel，重写没有返回值的run方法
        //executorService.submit();// 多用于callable，
    }


}

