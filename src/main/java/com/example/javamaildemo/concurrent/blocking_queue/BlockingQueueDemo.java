package com.example.javamaildemo.concurrent.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建阻塞队列: 数组的定长的队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        // 1st group - 异常
        System.out.println(blockingQueue.add("a"));// true
        System.out.println(blockingQueue.add("b"));// true
        System.out.println(blockingQueue.add("c"));// true
        System.out.println(blockingQueue.element());// a
//        System.out.println(blockingQueue.add("ww"));// 异常：Queue full
        System.out.println(blockingQueue.remove());// a - 先进先出，先删除"a"
        System.out.println(blockingQueue);// [b,c]

        // 2nd group - false，null
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("ww");// false
        blockingQueue.poll();// a
        blockingQueue.poll();// b
        blockingQueue.poll();// c
        blockingQueue.poll();// null

        // 3rd group - 阻塞
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("ww");// 阻塞...
        blockingQueue.take();// a
        blockingQueue.take();// b
        blockingQueue.take();// c
        blockingQueue.take();// null

        // 4th group -
        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        // 第四个阻塞，超过3s还放不进去就自动结束
        blockingQueue.offer("ww",3L, TimeUnit.SECONDS);

        blockingQueue.poll(3L,TimeUnit.SECONDS);

    }
}
