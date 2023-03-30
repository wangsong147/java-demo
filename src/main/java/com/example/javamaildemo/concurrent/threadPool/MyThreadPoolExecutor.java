package com.example.javamaildemo.concurrent.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 实际开发要用自定义线程：
//    原因 - fixed,single允许队列最大长度为Integer.MAX_VALUE，可能会oom
//          Cached,schedule允许最大线程数为Integer.MAX_VALUE，可能会oom
@Slf4j
public class MyThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5 - 2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()// main正在处理任务，多出来的任务返回给调用者线程运行了
        );

        try {
            // 线程池最大为5个，现在10个顾客来请求
            for (int i = 1; i <= 10; i++) {
                // execute - 提交一个任务给线程池进行执行，将其加入到线程池的任务队列中等待执行。
                // 线程池会从任务队列中取出任务，并将其分配给可用的线程进行处理
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在处理业务");
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 立即停止接受新的任务，但会把已经在队列中的任务全部执行完毕，然后关闭线程池，释放所有资源（包括线程和队列）。
            threadPoolExecutor.shutdown();
        }


    }
}
