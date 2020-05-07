package com.test;

import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-27 15:58
 * @Version 1.0
 * @Description CompleteFutureTest
 */
@RunWith(VertxUnitRunner.class)
public class CompleteFutureTest {

  @Test
  public void test1() throws Exception {
    CompletableFuture<Integer> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
          System.out.println("正在疯狂计算...");
          try {
            TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return 1 + 1;
        }
    );
    System.out.println("阻塞获取结果...");
    Integer integer = objectCompletableFuture.get();
    System.out.println(integer);

//    System.out.println("阻塞获取结果...");
//    Integer integer = objectCompletableFuture.getNow(222);
//    System.out.println(integer);

  }

  @Test
  public void test1(TestContext testContext) throws Exception {
//    Async async = testContext.async();

    CompletableFuture<Integer> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
          System.out.println("正在疯狂计算...");
          try {
            TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return 1 + 1;
        }
    );
//    System.out.println("阻塞获取结果...");
//    Integer integer = objectCompletableFuture.get();
//    System.out.println(integer);

//    System.out.println("阻塞获取结果...");
//    Integer integer = objectCompletableFuture.getNow(222);
//    System.out.println(integer);

  }

  @Test
  public void test2() {
    System.out.println(null instanceof String);
  }

}
