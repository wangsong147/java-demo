package com.example.javamaildemo.concurrent.read_write_lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁降级
 * 目的：
 *      读写锁降级的目的是为了减少锁的粒度，尽可能地提高程序的并发性能。
 *      具体来说，当一个持有写锁的线程需要获取读锁时，如果直接释放写锁再获取读锁会导致锁的竞争和资源的占用，降低程序的性能。
 *      因此，读写锁允许持有写锁的线程获取读锁，然后释放写锁，这就是读写锁的降级操作。经过降级操作后，该线程仍可保持对共享资源的独占访问权，同时也允许其他线程获取读锁。
 *      这样，就实现了从独占访问到共享访问的过渡，提高了程序的并发性能。
 */

public class WriteLockDown {
    public static void main(String[] args) {
        // 可重入读写锁
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        // 降级
        // 1.获取写锁
        writeLock.lock();
        // 2.获取读锁
        readLock.lock();
        // 3.释放写锁
        writeLock.unlock();
        // 3.释放读锁
        readLock.unlock();
    }
}
