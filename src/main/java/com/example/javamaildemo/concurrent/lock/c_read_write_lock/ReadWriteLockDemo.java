package com.example.javamaildemo.concurrent.lock.c_read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 资源类
class MyCache {
    // 创建map充当缓存,volatile修饰后修改会立即被其他线程看到，保证了多线程见对变量的可见性
    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        // todo
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写操作" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写完了" + key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // todo
            readWriteLock.writeLock().unlock();
        }

    }

    public Object get(String key) {
        readWriteLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "正在取操作" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "取完了" + key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
        return result;

    }


}

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        // todo 没加锁的时候，数据没有写完就会被读取，显然是不对的，所以应该加上读写锁
        MyCache myCache = new MyCache();
        //put
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(String.valueOf(num), num + "");
            }, String.valueOf(num)).start();
        }
        TimeUnit.MICROSECONDS.sleep(300);
        //get
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(String.valueOf(num));
            }, String.valueOf(num)).start();
        }
    }
}
