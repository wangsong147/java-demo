package com.example.javamaildemo.concurrent.thread;

public class MyThread1 extends Thread{
    @Override
    public void run() {
        Thread.currentThread().setName("MyThread1");
        System.out.println( Thread.currentThread().getName());
    }
}
