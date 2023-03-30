package com.example.javamaildemo.concurrent.sync;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("7颗龙珠已集齐！神龙即将出现...");
        });

        // 集齐7个龙珠
        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "星龙珠被收集");
                try {
                    // 达到NUMBER个线程都完成，才执行
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() +"阻塞之后的代码");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }

    }
}
