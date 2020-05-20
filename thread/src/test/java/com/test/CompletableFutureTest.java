package com.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-22 17:02
 * @Version 1.0
 * @Description CompletableFutureTest
 */
public class CompletableFutureTest {

  public static void main(String[] args) {

    CountDownLatch countDownLatch = new CountDownLatch(1);
    CompletableFuture<Integer> integerCompletableFuture = new CompletableFuture<>();

    CompletableFuture<Integer> integerCompletableFuture1 = CompletableFuture.completedFuture(3);
    try {
      Integer integer = integerCompletableFuture1.get();
      System.out.println(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    CompletableFuture<Integer> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
      try {
        System.out.println("进行一连串操作....");
        TimeUnit.SECONDS.sleep(3);
//        int i = 3 / 0;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      throw new NullPointerException("aa");
//      return 3;
    });
    System.out.println(integerCompletableFuture == uCompletableFuture);
    System.out.println("设置whenComplete方法....");
    CompletableFuture<Integer> integerCompletableFuture2 = uCompletableFuture.whenComplete((x, y) -> {
      System.out.println("执行完成！" + x);
      System.out.println("执行完成！" + y);
      throw new NullPointerException("222");
    });
    integerCompletableFuture2.whenComplete((x, y) -> {
      System.out.println("执行完成！" + x);
      System.out.println("执行完成！" + y);
      countDownLatch.countDown();
    });
    integerCompletableFuture2.exceptionally(x -> {
      System.out.println(x);
      return null;
    });
    System.out.println("返回的integerCompletableFuture2是否相同" + (integerCompletableFuture2 == uCompletableFuture));
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test1() throws Exception {
    CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread().getName()+"进行一连串操作1....");
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 1;
    }).thenApplyAsync(x -> {
      try {
        System.out.println(Thread.currentThread().getName()+"进行一连串操作2....");
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return x + 1;
    });
    CompletableFuture<Integer> future = future1.thenApplyAsync(x -> {
      try {
        System.out.println(Thread.currentThread().getName()+"进行一连串操作3....");
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return x + 1;
    });

    Integer integer = future.get();
    System.out.println(integer);

  }

  @Test
  public void test2() throws Exception {
    CountDownLatch countDownLatch = new CountDownLatch(1);

    CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread().getName()+"进行一连串操作1....");
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 1;
    });
    future1.thenRun(() -> {
      System.out.println(Thread.currentThread().getName()+"进行一连串操作2....");
      countDownLatch.countDown();
    });
    countDownLatch.await();
    System.out.println(1);

  }

}
