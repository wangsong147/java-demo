package com.example.javamaildemo.concurrent.lock.a_synchronized.wait_notify;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 1. 判断
 * 2. 执行
 * 3. 通知
 */
@Data
@Slf4j
class Share {
    // 总共30张票
    private Integer ticketNumber = 0;

    public synchronized void increment() throws InterruptedException {

        // 1.判断 - 不是0就等待一下，其他线程会执行-1
        while (ticketNumber != 0) {
            this.wait();
        }

        // 2.执行 - 如果number是0，就+1
        ticketNumber++;
        log.info(Thread.currentThread().getName() + "：：" + ticketNumber);

        // 3.通知 - 其他线程
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 判断不是0就等待一下，其他线程会执行+1
        while (ticketNumber != 1) {
            this.wait();
        }
        // 如果number是0，就-1
        ticketNumber--;
        log.info(Thread.currentThread().getName() + "：：" + ticketNumber);
        // 通知其他线程
        this.notifyAll();
    }

}

@Data
@Slf4j
public class SaleTicketForSynchronized {
    public static void main(String[] args) {
        Share share = new Share();
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