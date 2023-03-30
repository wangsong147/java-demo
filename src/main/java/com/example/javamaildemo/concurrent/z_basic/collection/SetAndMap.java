package com.example.javamaildemo.concurrent.z_basic.collection;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetAndMap {
    static Set<String> set = new CopyOnWriteArraySet<>();
    static Map<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        // create 30 threads
        for (int i = 0; i < 30; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                // add, 截取前面0-8索引包含的字符串
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                // get
                System.out.println(map); // ConcurrentModificationException
            }, key).start();
        }
    }


}