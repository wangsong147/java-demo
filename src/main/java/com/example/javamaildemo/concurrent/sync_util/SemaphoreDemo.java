package com.example.javamaildemo.concurrent.sync_util;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    private static final int PERMITS = 3;

    /**
     * 抢车位，6辆车，3个车位
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(PERMITS);
        // 6车
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    // 获取车位，如果没有车位，则等待进入阻塞状态
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+"------离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
