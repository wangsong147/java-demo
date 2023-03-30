package com.example.javamaildemo.concurrent.async_return;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class MyCompletableFuture {
    // Runnable
    public static void main(String[] args) throws Exception {
        // 异步调用，没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "::completableFuture1");
        });
        completableFuture1.get();
        // 异步调用，有返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "::completableFuture2");
//            try {
//                System.out.println("我要睡3s");
//                TimeUnit.SECONDS.sleep(3L);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            int a = 1/0;
            return 1024;
        });
        // 通过whenComplete方法注册了一个回调函数
        // 当异步方法完成时，它将被调用
        // 定义要执行的特定回调方法
        completableFuture2.whenComplete((result, error) -> {
            System.out.println("result=" + result);// 方法返回值
            System.out.println("error=" + error);// 异常信息
        }).get();

        System.out.println("你睡吧，我先执行了");

    }
}