package com.test.thread;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.*;

public class FutureDemo2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println(executorService.getClass());
        List<Future<String>> futureList = executorService.invokeAll(Lists.newArrayList(new Task("第一个任务", 1),
                new Task("第二个任务", 2), new Task("第三个任务", 3)));
        System.out.println(futureList.get(0).get());
    }

    @AllArgsConstructor
    static class Task implements Callable<String> {
        private String name;
        private int seconds;

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "   开始执行");
            TimeUnit.SECONDS.sleep(seconds);
            System.out.println(Thread.currentThread().getName() + "   执行完成");
            return Thread.currentThread().getName() + "->" + name;
        }
    }
}