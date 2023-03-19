//package com.example.javamaildemo;
//
//import com.example.javamaildemo.security.MyUserDetailsService;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//@SpringBootTest
//public class Testuuu {
//    @Resource
//    MyUserDetailsService myUserDetailsService;
//
//    @Test
//    void s() {
//        System.out.println(myUserDetailsService.loadUserByUsername("sss"));
//    }
//
//    public static void main(String[] args) {
//        List<Object> objects = new ArrayList<>();// ConcurrentModificationException
//        objects.add(1);
//        objects.add(2);
//        objects.add(3);
//        objects.add(4);
//        Iterator<Object> iterator = objects.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//            objects.add(6);
//        }
//    }
//}
