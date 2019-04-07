package com.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 东东 on 2019/4/1.
 */
public class Demo {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("----睡眠一秒-----");
            }
        });
        //默认为false,设置为false代表非守护线程,true为守护线程,守护线程在主方法结束时候结束
        thread.setDaemon(true);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程over");
    }

}
