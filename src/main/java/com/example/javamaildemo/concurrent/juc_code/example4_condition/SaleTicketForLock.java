package com.example.javamaildemo.concurrent.juc_code.example4_condition;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Data
@Slf4j
class CommunicateLock {
    // 标识位
    private int flag = 1;// 1 AA, 2BB, 3CC
    private Integer number = 0;
    // 创建Lock
    private final Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    // 整体循环10次{}
    // AA线程：flag - 1, 打印5次, 修改flag - 2, 通知BB
    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                c1.await();
            }
            for (int i = 0; i < 6; i++) {
                log.info(Thread.currentThread().getName() +"::"+ i + "轮数：" + loop);
            }
            //
        } finally {
            lock.unlock();
        }
    }

    public void increment() throws InterruptedException {
        // 上锁
        lock.lock();

        try {
            // 1.判断 - 不是0就等待一下，其他线程会执行-1
            while (number != 0) {
                c1.await();
            }

            // 2.执行 - 如果number是0，就+1
            number++;
            log.info(Thread.currentThread().getName() + "：：" + number);

            // 3.通知 - 其他线程
            c1.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 判断不是0就等待一下，其他线程会执行+1
            while (number != 1) {
                c1.await();
            }
            // 如果number是0，就-1
            number--;
            log.info(Thread.currentThread().getName() + "：：" + number);
            // 通知其他线程
            c1.signalAll();
        } finally {
            lock.unlock();
        }
    }

}


@Data
@Slf4j
public class SaleTicketForLock {
    public static void main(String[] args) {
        CommunicateLock share = new CommunicateLock();
        // 创建两个线程，调用卖票方法(下面调用的是同一个对象)
        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                try {
                    share.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                try {
                    share.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                try {
                    share.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "CC").start();

        new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                try {
                    share.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DD").start();

    }

}
