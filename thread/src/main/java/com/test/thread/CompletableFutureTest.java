package com.test.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-22 17:02
 * @Version 1.0
 * @Description CompletableFutureTest
 */
public class CompletableFutureTest {

  public static void main(String[] args) {

    CountDownLatch countDownLatch = new CountDownLatch(1);
    CompletableFuture<Integer> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
      try {
        System.out.println("进行一连串操作....");
        TimeUnit.SECONDS.sleep(3);
//        int i = 3 / 0;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      throw  new NullPointerException("aa");

//      return 3;
    });
    System.out.println("设置whenComplete方法....");
    uCompletableFuture.whenComplete((x, y) -> {
      System.out.println("执行完成！"+x);
      System.out.println("执行完成！"+y);
      countDownLatch.countDown();
    });
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
