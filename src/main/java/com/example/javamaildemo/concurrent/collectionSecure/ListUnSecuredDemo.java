package com.example.javamaildemo.concurrent.collectionSecure;

import lombok.Data;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class ListUnSecuredDemo {
    public static final String VECTOR = "VECTOR";
    public static final String COLLECTION = "COLLECTION";
    // 并发读，独立写：写的时候不改原数组，新复制一个数组修改新数组，最后再把新数组合并到原数组，实现原数组的修改
    // 签到
    public static final String COPY_ON_WRITE = "COPY_ON_WRITE";

    /**
     * 线程不安全演示：add没有synchronized
     * 在单线程环境下，向ArrayList添加元素是没有问题的。
     * 但是在多线程并发修改的情况下，若一个线程正在遍历ArrayList，另一个线程又在对其进行修改，就可能发生ConcurrentModificationException（并发修改异常）。
     * 具体来说，当一个线程在遍历ArrayList时，ArrayList内部会维护一个modCount计数器，用于记录其结构修改的次数。
     * 当另一个线程在插入、删除、修改元素时，modCount的值就会增加。此时，如果遍历线程发现modCount的值与遍历开始时的值不同，就会抛出ConcurrentModificationException异常。
     * 当你执行System.out.println(arraylist)时，会遍历ArrayList并输出所有元素。
     * 因此，当你在遍历时又对ArrayList进行了修改，就会触发并发修改异常。而只添加不输出不会触发异常，因为没有其它线程在遍历ArrayList。但是，这并不意味着不会发生问题，因为在多线程环境下，我们永远无法预知其它线程何时会访问ArrayList。
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = getSecuredList(COPY_ON_WRITE);
        // create 30 threads
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // add, 截取前面0-8索引包含的字符串
                list.add(UUID.randomUUID().toString().substring(0, 8));
                // get
                System.out.println(list); // ConcurrentModificationException
            }).start();
        }
    }

    // 解决方法
    public static List<String> getSecuredList(String type) {
        if (type.equals(VECTOR)) return new Vector<>();
        if (type.equals(COLLECTION)) return Collections.synchronizedList(new ArrayList<>());
        if (type.equals(COPY_ON_WRITE)) return new CopyOnWriteArrayList<>();
        throw new RuntimeException("参数异常");
    }
}
