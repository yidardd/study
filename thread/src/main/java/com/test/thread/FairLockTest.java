package com.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockTest {

    //默认是不公平的
    /**
     * Creates an instance of {@code ReentrantLock}.
     * This is equivalent to using {@code ReentrantLock(false)}.
    public ReentrantLock() {
        sync = new NonfairSync();
    }
     */
//    private Lock lock = new ReentrantLock();

    //公平锁
    private Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        //简单的使用
        test1();
    }

    public static void test1() {
        List<Thread> threadList = new ArrayList<>();
        FairLockTest lockTest = new FairLockTest();
        for (int i = 0; i < 10; i++) {
            threadList.add(   //线程1
                    new Thread(() -> {
                        lockTest.method1(Thread.currentThread());
                    }, i + ""));
        }

        for (Thread thread : threadList) {
            System.out.println("线程名" + thread.getName() + "启动");
            thread.start();
        }

    }

    //需要参与同步的方法
    private void method1(Thread thread) {
        lock.lock();
        try {
            System.out.println("线程名" + thread.getName() + "获得了锁");
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程名" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

}