package com.test.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 东东 on 2019/4/6.
 */
public class ReadWriteLockTest {

    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    private static Integer count = 0;

    public static void main(String[] args) {
        ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
        for (int i = 0; i < 100; i++) {
            //从1到10的int型随数
            int j = (int) (1 + Math.random() * (10 - 1 + 1));
            if (j % 4 != 1) {
                //写操作
                new Thread("" + i) {
                    public void run() {
                        readWriteLockTest.get(Thread.currentThread());
                    }
                }.start();
            } else {
                //读操作
                int co = i;
                new Thread("" + i) {
                    public void run() {
                        readWriteLockTest.write(Thread.currentThread(), co);
                    }
                }.start();
            }
        }

    }

    public static void get(Thread thread) {
        rwl.readLock().lock();
        try {
            System.out.println("线程" + thread.getName() + "开始读操作...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程" + thread.getName() + "读操作完毕..." + count);
        } finally {
            rwl.readLock().unlock();
        }
    }

    public static void write(Thread thread, int i) {
        rwl.writeLock().lock();
        try {
            System.out.println("线程" + thread.getName() + "开始写操作---------"+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count = i;
            System.out.println("线程" + thread.getName() + "开始写操作完成------"+i);
        } finally {
            rwl.writeLock().unlock();
        }
    }


}
