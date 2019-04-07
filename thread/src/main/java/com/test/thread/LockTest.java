package com.test.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

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
        //trylock
//        test2();
//        //中断锁
//        test3();
    }

    public static void test1() {
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 10; i++) {
            //线程1
            new Thread(() -> {
                lockTest.method1(Thread.currentThread());
            }, i + "").start();
        }
    }

    //需要参与同步的方法
    private void method1(Thread thread) {
        //错误
//        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("线程名" + thread.getName() + "获得了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程名" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void test2() {
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 10; i++) {
            //线程1
            new Thread(() -> {
                lockTest.method2(Thread.currentThread());
            }, i + "").start();
        }
    }

    //需要参与同步的方法
    private void method2(Thread thread) {

//        尝试获取锁
        boolean b = lock.tryLock();

        //        尝试获取锁时间
//        boolean b = false;
//        try {
//            System.out.println("线程名" + thread.getName() + "尝试获取锁--");
//            b = lock.tryLock(2, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (!b) {
            //未获取到锁
            System.out.println("线程名" + thread.getName() + "没有获取到锁,直接返回--");
            return;
        }
        //获取到锁
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("线程名" + thread.getName() + "获得了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程名" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void test3() {
        LockTest lockTest = new LockTest();
        //线程1
        Thread t1 = new Thread(() -> {
            try {
                lockTest.method3(Thread.currentThread());
            } catch (InterruptedException e) {
                System.out.println("线程名t1中断-----");
//                e.printStackTrace();
            }
        }, "t1");
        t1.start();
//线程1
        Thread t2 = new Thread(() -> {
            try {
                lockTest.method3(Thread.currentThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程名t1执行中断");
        t1.interrupt();
    }

    //需要参与同步的方法
    private void method3(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        //获取到锁
        System.out.println("线程名" + thread.getName() + "获得了锁");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            System.out.println("线程名" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

}