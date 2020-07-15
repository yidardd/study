package com.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-22 14:30
 * @Version 1.0
 * @Description ThreadUnSafeDemo
 */
public class ThreadUnSafeDemo {

//      private static AtomicInteger value = new AtomicInteger(1);
  private static int value = 1;

  public static void main(String[] args) throws InterruptedException {

    CountDownLatch latch = new CountDownLatch(1);

    for (int i = 0; i < 5; i++) {
      CompletableFuture.runAsync(() -> {
        while (true) {
          System.out.println("正在出票" + (value++) + "");
//            safeMethod();
          if (value > 80) {
            latch.countDown();
          }
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }
    latch.await();
    System.out.println("执行完毕...");
  }

  public synchronized static void safeMethod() {
    System.out.println("正在出票" + (value++) + "");
  }

}
