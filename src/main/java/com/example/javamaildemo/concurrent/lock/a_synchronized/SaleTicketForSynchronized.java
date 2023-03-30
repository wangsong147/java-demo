package com.example.javamaildemo.concurrent.lock.a_synchronized;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 1.创建资源类（空调），在资源类创建属性和操作方法（除湿，制冷）
 * 2.创建多个线程，调用资源类的操作方法
 * 例子：3个售票员，卖出30张票
 */
@Data
@Slf4j
class Ticket {
    // 总共30张票
    private Integer ticketNumber = 30;

    public synchronized void sale() {
        if (ticketNumber > 0) {
            log.info("当前线程名:{} - 卖出:{} - 剩余:{}",
                    Thread.currentThread().getName(), ticketNumber--, ticketNumber);
        }
    }

}

@Data
@Slf4j
public class SaleTicketForSynchronized {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // 创建三个线程，调用卖票方法(下面调用的是同一个对象)
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "CC").start();

    }

}