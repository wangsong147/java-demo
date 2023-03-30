package com.example.javamaildemo.concurrent.fork_join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class Test{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyForkJoinTask task = new MyForkJoinTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoin = forkJoinPool.submit(task);
        Integer integer = forkJoin.get();
        System.out.println(integer);
        forkJoinPool.shutdown();
    }
}
public class MyForkJoinTask extends RecursiveTask<Integer> {
    // 差值不能超过10
    private static final Integer VALUE = 10;
    private int begin;
    private int end;
    private int result;

    MyForkJoinTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    // 拆分合并过程
    @Override
    protected Integer compute() {
        //  1+2+...+100,二分法拆分相加，相差大于10-就2分拆分
        if (end - begin <= VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            // 获取中间值
            int middle = (end + begin) / 2;
            // 拆分左边
            MyForkJoinTask f1 = new MyForkJoinTask(begin, middle);
            // 拆分右边
            MyForkJoinTask f2 = new MyForkJoinTask(middle + 1, end);
            // 调用方法拆分
            f1.fork();
            f2.fork();
            // 合并结果
            result = f1.join() + f2.join();
        }
        return result;
    }
}

