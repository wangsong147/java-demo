package com.example.javamaildemo.controller;

import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.vo.MailVo;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JdkController {
    private static List<Person> list;

    static {
        JdkController.list = new ArrayList<>();
        list.add(new Person(1L, "老莫", 35));
        list.add(new Person(3L, "李三", 20));
        list.add(new Person(2L, "王二", 20));
//        list.add(null);list.add(null);
        list.add(new Person(4L, "启强", 50));
        list.add(new Person(4L, "启强2号", 50));
    }

    public static void main(String[] args) {
        // 中间操作
        sort();// 重点在Comparator的api
        filter();
        limit();
        // 结束操作，重点在Collectors的api
        listToMap();
        listToStr();// 只有map成str才能joining成str
        groupBy();
        partition();
        count();
        peek();// 这里执行完之后，list里的所有age都变成0，下面用的时候要注意

        optional();
        builder();
        // 1.实例创建 2.api调用(静态or非静态)
    }

    public static void builder() {
        Person mrAn = Person.builder().id(9L).name("安局长").age(66).build();
        System.out.println("【builder】" + mrAn);
    }

    public static void optional() {
        Person mrAn = Person.builder().id(9L).name("安局长").age(66).build();
        // 创建
        Optional<Person> person = Optional.of(mrAn);// 传入值为null时，回在of方法报空指针
        Optional<Person> nullAble = Optional.ofNullable(null);// 传null，get时会报空指针
        Optional<Object> empty = Optional.empty();// 传null，get时会报空指针

        // 获取
        System.out.println("【optional：get】" + person.get() + "\t");
        // 判断
        System.out.println("【optional：isPresent】" + person.isPresent() + "\t");
        person.ifPresent(p -> System.out.println("【optional：ifPresent】" + p.getName() + "\t"));
        // 设置默认值
        Person orElsePerson = person.orElse(getPerson());// 参数是Person类型，直接赋值
        Person orElseGetPerson = person.orElseGet(JdkController::getPerson);// 参数是函数式接口，只有在没有值的时候才会调用接口实现类的方法，得到一个Person类

        Object orElseObj = empty.orElse(getPerson());
        Object orElseGetObj = empty.orElseGet(JdkController::getPerson);

    }

    private static Person getPerson() {
        System.out.println("【optional: orElse/orElseGet】getPerson()被打印了");
        return Person.builder().id(100L).name("默认值").age(2000).build();
    }

    public static void groupBy() {
//        Map<Integer, List<Person>> collect = list.stream().collect(Collectors.groupingBy(person -> person.getAge()));
//        System.out.println("【groupBy】" + collect);
        System.out.println("【groupBy】" + list.stream().collect(Collectors.groupingBy(p -> p.getId() > 2)));
    }

    public static void partition() {
        System.out.println("【partition】" + list.stream().collect(Collectors.partitioningBy(p -> p.getId() > 2)));
    }

    public static void count() {
        System.out.println("【count】" + list.stream().limit(2).count());
    }

    public static void limit() {
        System.out.println("【limit】" + list.stream().limit(2).collect(Collectors.toList()));
    }

    public static void listToMap() {
        System.out.println("【toMap】" + list.stream().collect(Collectors.toMap(
                Person::getId,
                v -> v,
                (person, person2) -> Person
                        .builder()
                        .id(person.getId())
                        .name(person.getName() + person2.getName())
                        .age(person.getAge())
                        .build()
        )));
    }

    public static void listToStr() {
        String toStr = list.stream().map(Person::getName).collect(Collectors.joining(",", "list_", "_toStr"));
        System.out.println("【toStr】" + toStr);
    }

    public static void filter() {
        System.out.println("【filter】" + list.stream().filter(person -> person.getId() > 2).collect(Collectors.toList()));
    }

    public static void sort() {
        // 从小到大byAge
        System.out.println("【sort】从小到大byAge：" + list.stream().sorted(Comparator.comparingInt(Person::getAge)).collect(Collectors.toList()));
        // 从大到小byAge
        System.out.println("【sort】从大到小byAge：" + list.stream().sorted(Comparator.comparingInt(Person::getAge).reversed()).collect(Collectors.toList()));
        // 从小到大byId
        System.out.println("【sort】从小到大byId： " + list.stream().sorted(Comparator.comparingLong(Person::getId)).collect(Collectors.toList()));
        // id一样比age
        System.out.println("【sort】age一样比id：  " + list.stream().sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getId)).collect(Collectors.toList()));
        // list中有null值的情况: 可以避免nullPointException
        // 结果：[Person(id=4, name=顺子, age=50), Person(id=3, name=李四, age=20), Person(id=2, name=王五, age=20), Person(id=1, name=张三, age=35), null, null]
        System.out.println("【sort】List有null值： " + list.stream().sorted(Comparator.nullsFirst(Comparator.comparing(Person::getId)).reversed()).collect(Collectors.toList()));


    }

    public static void peek() {
        System.out.println("【peek】       " + list.stream().peek(person -> person.setAge(0)).collect(Collectors.toList()));
    }


}
