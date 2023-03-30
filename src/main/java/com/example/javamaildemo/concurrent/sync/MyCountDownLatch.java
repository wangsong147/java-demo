package com.example.javamaildemo.concurrent.sync;

import java.util.concurrent.CountDownLatch;

/**
 * 同步的工具类，它的作用是在某个线程等待一定数量的线程执行完毕后再继续执行.
 * CountDownLatch可以帮助我们将多线程并发任务分成若干子任务，等待所有子任务执行完毕后再执行后续操作，避免因为并发执行导致的资源竞争和执行错误
 */
// 等待计数器归0
public class MyCountDownLatch {
    // 例子：6个同学陆续离开教室，班长才可以最后锁门
    // Main线程在执行完main方法后就结束了，而自己创建的线程可以一直运行。
    // start方法将线程创建出来交给cpu等待调度，之后就继续执行main方法剩下的代码，
    //      而交给cpu的线程可能马上就北大调用，也可能main方法执行完成之后再调用
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(6);
        // 模拟6个学生
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号同学离开了教室");
                // 计数 -1
                count.countDown();
            }, String.valueOf(i)).start();
        }
        // count不是0就会执行await
        // main线程调用了CountDownLatch对象的await方法，main线程被放入该对象的等待序列中
        count.await();// 当前线程调用了count对象的await方法
        // 这样做班长可能在1号同学走了以后就关门，不会同步(按照顺序)执行任务
        System.out.println(Thread.currentThread().getName() + "班长锁门走人了");


    }

}
