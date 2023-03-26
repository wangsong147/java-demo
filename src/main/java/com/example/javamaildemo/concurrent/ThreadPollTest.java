package com.example.javamaildemo.concurrent;

import com.example.javamaildemo.concurrent.callable.MyCallable1;
import com.example.javamaildemo.concurrent.runnable.NumberThread1;
import com.example.javamaildemo.concurrent.runnable.NumberThread2;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPollTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 造了10个线程，至于做什么要自定义
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        // 用实现类管理线程池(接口属性是常量不能设置)
        threadPoolExecutor.setCorePoolSize(5);
        threadPoolExecutor.setKeepAliveTime(5L,TimeUnit.MINUTES);

        // 指定自己要什么事情
        // 多用于runnabel，重写没有返回值的run方法
        executorService.execute(new NumberThread1());
        executorService.execute(new NumberThread2());
        // 多用于callable,有返回值
        Future<String> future = executorService.submit(new MyCallable1());
        String s = future.get();
        log.info(s);

        // 关闭线程池
        executorService.shutdown();

        Thread thread = new Thread(new NumberThread1());
        thread.start();
    }


}

