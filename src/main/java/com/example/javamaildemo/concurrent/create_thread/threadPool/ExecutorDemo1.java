package com.example.javamaildemo.concurrent.create_thread.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ExecutorDemo1 {
    public void sevenParams() {
        /**
         * 创建线程池底层都是用了ThreadPoolExecutor来创建线程池，一共有7个参数
         * 1 - int corPoolSize，常驻线程数(有10个窗口，但是平常只开放5个窗口)
         * 2 - int maximumPoolSize，线程池最大线程数(10个窗口全开)
         * 3 - long keepAliveTime，非核心线程空闲时的存活时间
         * 4 - TimeUnit unit ，时间单位
         * 5 - BlockingQueue<Runnable> workQueue，阻塞队列
         * 6 - ThreadFactory threadFactory，线程工厂
         * 7 - RejectedExecutionHandler handler，拒绝策略
         *
         *
         * 1 - corePoolSize：核心线程数，即线程池中始终保持的线程数，即使它们处于空闲状态。核心线程会一直存活，即使它们处于闲置状态，除非将ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true。当新任务在方法execute()中提交时，如果运行的线程少于corePoolSize，则创建新线程来处理请求，即使有其他空闲的线程可用。
         *
         * 2 - maximumPoolSize：最大线程数，即线程池中允许的最大线程数。当活动线程达到此数量后，后续的新任务将被阻塞，直到线程池中有可用线程。如果将此参数设置为无限大，则maximumPoolSize的值就没有实际意义。
         *
         * 3 - keepAliveTime：线程空闲时间，即当线程没有任务执行时，存活多久就会被销毁。如果设置了allowCoreThreadTimeOut为true，那么核心线程也会在空闲时间超时后被销毁。
         *
         * 4 - unit：空闲时间的单位，例如TimeUnit.SECONDS、TimeUnit.MILLISECONDS等。
         *
         * 5 - workQueue：任务队列，用于保存等待执行的任务的阻塞队列。可以选择不同的阻塞队列实现，例如ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue等。
         *
         * 6 - threadFactory：线程工厂，用于创建线程。可以自定义线程工厂，实现createThread()方法来创建线程的一部分属性，例如线程的命名格式、是否为守护线程、优先级等。
         *
         * 7 - handler：拒绝策略，用于处理任务队列已满且无法继续添加任务的情况。有四种拒绝策略：AbortPolicy（默认），CallerRunsPolicy、DiscardOldestPolicy、DiscardPolicy。可以根据自己的需求自定义拒绝策略。
         */

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10,
                15,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(15)
        );
    }

    public static void main(String[] args) {
        // 1池5线程 - 1对n: 多余的线程在外面等待
        // 5个银行窗口
        ExecutorService thread1 = Executors.newFixedThreadPool(5);
        ExecutorService singleThread = Executors.newSingleThreadExecutor();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        try {
            // 10个顾客访问
            for (int i = 1; i <= 10; i++) {
                // execute - 提交一个任务给线程池进行执行，将其加入到线程池的任务队列中等待执行。
                // 线程池会从任务队列中取出任务，并将其分配给可用的线程进行处理
                cachedThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在处理业务");
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 立即停止接受新的任务，但会把已经在队列中的任务全部执行完毕，然后关闭线程池，释放所有资源（包括线程和队列）。
            cachedThreadPool.shutdown();
        }


    }
}
