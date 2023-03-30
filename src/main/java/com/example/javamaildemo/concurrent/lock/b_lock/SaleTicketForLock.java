package com.example.javamaildemo.concurrent.lock.b_lock;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;


@Data
@Slf4j
class TicketL {
    // 票数量
    private Integer ticketNumber = 30;

    // 创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(true);// 公平锁，对线程雨露均沾

    public synchronized void sale() {
        // 上锁
        lock.lock();
        try {
            // 判断是否有票
            if (ticketNumber > 0) {
                log.info("当前线程名:{} - 卖出:{} - 剩余:{}",
                        Thread.currentThread().getName(), ticketNumber--, ticketNumber);
            }
        } finally {
            lock.unlock();
        }
    }

}

public class SaleTicketForLock {
    public static void main(String[] args) {
        TicketL ticket = new TicketL();
        // 创建 三个线程，调用卖票方法(下面调用的是同一个对象)
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        }, "CC").start();
    }
}
