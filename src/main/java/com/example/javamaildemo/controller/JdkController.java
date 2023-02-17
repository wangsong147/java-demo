package com.example.javamaildemo.controller;

import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.vo.MailVo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class JdkController {
    private static List<Person> list;

    static {
        JdkController.list = new ArrayList<>();
        list.add(new Person(1L, "张一", 35));
        list.add(new Person(3L, "李三", 20));
        list.add(new Person(2L, "王二", 20));
//        list.add(null);list.add(null);
        list.add(new Person(4L, "顺四", 50));
    }

    public static void main(String[] args) {
        sort();
        peek();

    }

    public static void sort() {
        // 从小到大byAge
        System.out.println("从小到大byAge：" + list.stream().sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList()));
        // 从大到小byAge
        System.out.println("从大到小byAge：" + list.stream().sorted(Comparator.comparingInt(Person::getAge).reversed()).collect(Collectors.toList()));
        // 从小到大byId
        System.out.println("从小到大byId： " + list.stream().sorted(Comparator.comparingLong(Person::getId)).collect(Collectors.toList()));
        // id一样比age
        System.out.println("id一样比age：  " + list.stream().sorted(Comparator.comparing(Person::getId).thenComparing(Person::getAge)).collect(Collectors.toList()));
        // list中有null值的情况: 可以避免nullPointException
        // 结果：[Person(id=4, name=顺子, age=50), Person(id=3, name=李四, age=20), Person(id=2, name=王五, age=20), Person(id=1, name=张三, age=35), null, null]
        System.out.println("List有null值： " + list.stream().sorted(Comparator.nullsFirst(Comparator.comparing(Person::getId)).reversed()).collect(Collectors.toList()));


    }

    public static void peek() {
        System.out.println("peek:         " + list.stream().peek(person -> person.setAge(0)).collect(Collectors.toList()));
    }

    public static void builder() {
    }

    public static void optional3() {
    }

    public static void optional4() {
    }


}
