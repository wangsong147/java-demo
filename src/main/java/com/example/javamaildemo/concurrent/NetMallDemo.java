package com.example.javamaildemo.concurrent;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

// 功能 - 性能
public class NetMallDemo {
    // 获取电商
    public static List<NetMall> list = Arrays.asList(
            new NetMall("京东"),
            new NetMall("淘宝"),
            new NetMall("当当")
            , new NetMall("当当1")
            , new NetMall("当当2")
    );

    public static List<String> getPrice(List<NetMall> mall, String productName) {
        return mall.stream()
                .map(netMall -> String.format(productName + "在%s的价格是%.2f", netMall.getNetMallName(), netMall.getPrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByAsync(List<NetMall> mall, String productName) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        List<String> collect = mall.stream()
                // 和同步是一样的，要3s
                // .map(netMall -> CompletableFuture.supplyAsync(() -> String.format(productName + "在%s的价格是%.2f", netMall.getNetMallName(), netMall.getPrice(productName))).join())
                // 把任务全部先加入队列执行
                .map(netMall -> CompletableFuture.supplyAsync(
                        () -> String.format(productName + "在%s的价格是%.2f", netMall.getNetMallName(), netMall.getPrice(productName)), threadPool))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)// join放在这里，让任务先执行，不阻塞
                .collect(Collectors.toList());
        threadPool.shutdown();
        return collect;
    }

    public static void main(String[] args) {
        System.out.println(CompletableFuture.supplyAsync(() -> 1)
                .handle((r,e) -> {
                    int i = 1 / 0;
                    return r + 1;
                })
                .handle((r,e) -> {
                    System.out.println("222222");
                    return r + 1;
                })
//                        .thenApply(r -> {
//                    int i = 1 / 0;
//                    return r + 1;
//                }).thenApply(r -> {
//                    System.out.println("222222");
//                    return r + 1;
//                })
                .whenComplete((r, e) -> {
                    System.out.println("whenComplete:" + r);
                    System.out.println("whenComplete:" + e);
                }).exceptionally(e -> {
                    System.out.println("exceptionally:" + e.getMessage());
                    return 9;
                }).join());
        // 单线程做法
//        System.out.println(getPrice(list, "mysql"));
//        long start = System.currentTimeMillis();
//        // 多线程优化
//        System.out.println(getPriceByAsync(list, "mysql"));
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }
}

// 电商网站
@Data
@Accessors(chain = true)
class NetMall {
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double getPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 模拟价格
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);

    }

}