package com.example.javamaildemo;

import com.example.javamaildemo.vo.MailVo;
import com.google.common.html.HtmlEscapers;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
class JavaMailDemoApplicationTests {
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcOperations jdbcOperations;

    @Test
    void testStr() {
    }

    @Test
    void demo() {
        String ss = "ss";
        boolean b = "abcdefgh".regionMatches(true, 2, ss, 3, 4);
        StringBuffer we = new StringBuffer();
        System.out.println(Integer.decode("99"));
        String rep = "wangwzsls";
        System.out.println(rep.replace('w', ' '));
        System.out.println(rep.replace("w", ""));

        IntStream.range(1, 10).limit(5).forEach(System.out::println);
        IntStream.of(1, 2, 3).forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 2, 3);
        Stream.generate(() -> integers).limit(4).forEach(System.out::println);
        // 时间戳
        long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime time2localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneId.systemDefault());
        System.out.println("时间戳：" + l);
        System.out.println("localdatetime：" + time2localDateTime);


        // Date <-> LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.of("GMT+8"));
        Date date = Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));
        System.out.println("LocalDateTime：" + localDateTime);
        System.out.println("Date：" + date);

    }

    public static void main(String[] args) throws Exception {
        String fileName = "../a/file/name/test/";
        Path path = Paths.get(fileName);
        System.out.println(path.getFileName().toString());
        System.out.println("\n");

        URL url = new URL("https://www.baidu.com/data");
        if (!url.getProtocol().startsWith("http") || !url.getProtocol().startsWith("https"))
            throw new RuntimeException("不是http或https协议");
        InetAddress inetAddress = InetAddress.getByName(url.getHost());
        if (inetAddress.isAnyLocalAddress() || inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress()) {
            throw new RuntimeException("112");
        }
//        String str = "abc";
//        String input = "Abcabcabcd123";
//        System.out.println(input.contains(str.toLowerCase()));
//        System.out.println(Pattern.compile(str, Pattern.CASE_INSENSITIVE).matcher("Abcabcabcd123").find());
    }

    @Test
    void testOptional() {
        List<String> nameList = new ArrayList<>();
        nameList.add("Darcy");
        nameList.add("Mike");
        nameList.add("Tom");
        List<String> upperCaseNameList = nameList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        upperCaseNameList.forEach(name -> System.out.println(name + ","));
    }

    @Test
    void t() {
        List<MailVo> mailVos = new ArrayList<>();
        mailVos.add(new MailVo(4L, "from1", "to", 5));
        mailVos.add(new MailVo(6L, "from1", "to", 1));
//        mailVos.add(null);
        mailVos.add(new MailVo(1L, "from2", "to", 1));
//        mailVos.add(null);
        mailVos.add(new MailVo(5L, "from2", "to", 4));

//        IntStream intStream = mailVos.stream().mapToInt(MailVo::getAge);

        //        String s = mailVos.stream().map(MailVo::toString).collect(Collectors.joining(","));
//        System.out.println(s+"\n");
//Stream.generate()
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

        Map<String, List<MailVo>> collect = mailVos.stream()
                .collect(Collectors.groupingBy(mailVo -> mailVo.getFromMail() + "11"));
        collect.forEach((k, v) -> System.out.println(k + ":" + v));

        Map<Boolean, List<MailVo>> collect1 = mailVos.stream()
                .collect(Collectors.partitioningBy(a -> a.getAge() > 0));
        collect1.forEach((k, v) -> System.out.println(k + ":" + v));

//        // Comparator静态方法
//        mailVos.stream().sorted(Comparator.comparing(MailVo::getId).reversed()).forEach(System.out::println);System.out.println("\n");
//        // 对象方法
//        mailVos.stream().sorted((x,y)->Long.compare(y.getId(),x.getId())).forEach(System.out::println);System.out.println("\n");
//
//        mailVos.stream().sorted((x,y)->y.getAge()-x.getAge()).forEach(System.out::println);
//
//        // nullsFirst(): 1.处理null值，避免空指针异常  2.把null排在前面
//        System.out.println("=============");
//        mailVos.stream().sorted(Comparator.nullsFirst(Comparator.comparing(MailVo::getId).reversed())).forEach(System.out::println);
//        // thenComparing(): 前者相等的时候，按后者比较
//        // reversed(): 针对的是整个数组进行一个反转
//        System.out.println("=============");
//        mailVos.stream().sorted(Comparator.comparing(MailVo::getAge).thenComparingLong(MailVo::getId)).forEach(System.out::println);
    }

    @Test
    void stream() throws IOException {

    }

    @Test
    void formatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年-MM月-dd日");
        String format = formatter.format(LocalDateTime.now());
        TemporalAccessor parse = formatter.parse("2023年-01月-18日");
        System.out.println(formatter.format(LocalDateTime.now()));
        System.out.println(parse);
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


        String s = collection.stream().map(MailVo::getTo).reduce("", (a, b) -> a + b);  // 头部拼接一个str
        System.out.println(s);

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

        integers2.add(new Integer(3));
        integers2.add(new Integer(4));


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
