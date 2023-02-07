package com.example.javamaildemo;

import com.example.javamaildemo.vo.MailVo;
import org.assertj.core.internal.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.text.Collator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class JavaMailDemoApplicationTests {
    @Autowired
    JavaMailSender mailSender;

    @Test
    void leetCode() throws IOException {
        URL url = new URL("https://www.bilibili.com/video/BV1Kb411W75N/?p=629&spm_id_from=pageDriver&vd_source=95cab191d2db549153ec03e21f31");
        System.out.println(url.getPort());
        System.out.println(url.getUserInfo());
        UserDetailsService detailService;
        User user;
        UserDetails userDetails;
        /*
         *
         *
         *
         * */
        int[] nums = {1, 2, 3};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                System.out.println("true");
                return;
            }
            map.put(num, num);
        }
        System.out.println("false");
    }

    @Test
    void formatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年-MM月-dd日");
        String format = formatter.format(LocalDateTime.now());
        TemporalAccessor parse = formatter.parse("2023年-01月-18日");
        System.out.println(formatter.format(LocalDateTime.now()));
        System.out.println(parse);

        Date date1 = new Date();
        Date date2 = new Date(11111111111L);
    }

    @Test
    void testSendEmail() {
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        /*
         * 553 Mail from must equal authorized user
         * 发件人必须和授权用户一致
         */
        simpMsg.setFrom("song5_wang@163.com");
//        simpMsg.setFrom("ws18740278017@163.com");
        simpMsg.setTo("18945588017@163.com");
        simpMsg.setSubject("subject");
        simpMsg.setText("content");
        mailSender.send(simpMsg);
        String conflicts = "conflicts".toUpperCase();// 1234567
        // 我再提交一个冲突再ws分支kkk
    }

    @Test
    void testCollection() {
        MailVo mailVo1 = new MailVo();
        mailVo1.setFromMail("from_1");
        mailVo1.setTo("to_1");

        MailVo mailVo2 = new MailVo();
        mailVo2.setFromMail("from_2");
        mailVo2.setTo("to_2");

        MailVo mailVo3 = new MailVo();
        mailVo3.setFromMail("from_3");
        mailVo3.setTo("to_3");

        Collection<MailVo> collection = new ArrayList<>(20);
        collection.add(mailVo1);
        collection.add(mailVo2);
        collection.add(mailVo3);
        System.out.println(collection);

        Optional<String> reduce = collection.stream().map(MailVo::getTo).reduce((a, b) -> a + b);//
        reduce.ifPresent(System.out::println);

        String s = collection.stream().map(MailVo::getTo).reduce("", (a, b) -> a + b);  // 头部拼接一个str
        System.out.println(s);


//        System.out.println(collection.contains(mailVo2)); // true
//        collection.remove(mailVo2); // 默认尾删
        for (MailVo mailVo : collection) {
            mailVo.setFromMail("0");
            mailVo.setTo("0");
        }
        System.out.println(collection);

        // ================================================================================================
        Collection<Integer> integers1 = new ArrayList<>();
        integers1.add(1);
        integers1.add(2);
        integers1.add(3);
        integers1.add(4);
        integers1.add(4);
        System.out.println(integers1);
        Set<Integer> collect = integers1.stream().collect(Collectors.toSet()); //去重
        System.out.println(collect);
        if (true) return;

        Collection<Integer> integers2 = new ArrayList<>();
        integers2.add(new Integer(1));
        integers2.add(new Integer(2));
//        integers2.add(7);
        integers2.add(new Integer(3));
        integers2.add(new Integer(4));
//        System.out.println(integers2);

//        integers1.removeAll(integers2);
//        System.out.println(integers1);
//        integers1.retainAll(integers2);
//        System.out.println(integers1);

        for (Integer integer : integers2) {
            int i = integer.intValue();
        }
        System.out.println(integers2);


    }


    @Test
    void calendar() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }


}
