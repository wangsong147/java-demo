package com.example.javamaildemo.concurrent.a_completableFuture;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data
@Accessors(chain = true)
// 链式编程
class ChainCode {
    private String str;
    private Integer num;

    public static void main(String[] args) {
        ChainCode chainCode = new ChainCode();
        chainCode.setNum(1).setStr("str");
    }
}

@Slf4j
public class MyCompletableFuture {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        try {
            CompletableFuture.supplyAsync(() -> "ok", threadPool)
                    .whenComplete((result, exception) -> {
                        if (exception == null) {
                            System.out.println("无异常，返回值为：" + result);
                        }
                    }).exceptionally(Throwable::getMessage);
        } catch (Exception e) {
            log.info(e.getCause() + e.getMessage());
        } finally {
            // todo 一定要关，线程池中的线程会一直阻塞等待新任务，所以不关会导致oom
            threadPool.shutdown();
        }


    }
}
