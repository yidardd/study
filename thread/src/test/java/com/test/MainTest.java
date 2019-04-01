package com.test;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 东东 on 2019/3/28.
 */
public class MainTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("开始执行-----");
        Future<String> future = Executors.newCachedThreadPool().submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕----");
            return "success";
        });
        System.out.println("-----异步  " + LocalDateTime.now());
        String s = future.get();
        System.out.println("----同步   " + LocalDateTime.now());
        System.out.println("返回结果: "+s);
    }
}
