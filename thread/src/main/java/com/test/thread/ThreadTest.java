package com.test.thread;

public class ThreadTest {
    public static void main(String[] args) {
        ThreadModel testThread1 = new ThreadModel();
        testThread1.setName("我是线程1");
        testThread1.start();
 
        ThreadModel testThread2 = new ThreadModel();
        testThread2.setName("我是线程2");
        testThread2.start();
    }
}
 
/**
 * Create by Kenson on 2019/4/3
 */
 
 class ThreadModel extends Thread {

    static int r = 0;

    @Override
    public void run() {
        synchronized (ThreadModel.class) {
            for (int i = 0; i < 5; i++) {
                ThreadModel.class.notify();
                System.out.println(Thread.currentThread().getName() + ":" + r);
                r++;
                try {
                    ThreadModel.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}