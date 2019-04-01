package com.test.thread;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by 东东 on 2019/3/28.
 */
public class CompletionTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Task> taskList = Lists.newArrayList(new Task("第一个任务", 1),
                new Task("第二个任务", 2), new Task("第三个任务", 3));

        ExecutorCompletionService completionService = new ExecutorCompletionService<String>(executorService);

        taskList.forEach(completionService::submit);

        Future take = completionService.take();
        System.out.println("获取最先执行的任务的结果" + take.get());
    }

    @AllArgsConstructor
    private static class Task implements Callable<String> {
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


