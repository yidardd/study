package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

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
  public void testEx() {
    CompletableFuture<Integer> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
          System.out.println("正在疯狂计算...");
          try {
            TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          return 1 / 0;
        }
    );
    System.out.println("阻塞获取结果...");
    try {
      Integer integer = objectCompletableFuture.get();
      System.out.println(integer);
    } catch (Exception e) {
      System.out.println(1);
      e.printStackTrace();
    }

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
  public void allOfTest2() throws Exception {
    List<CompletableFuture> comlist = new ArrayList<>();
    int size = 3;
    CountDownLatch countDownLatch = new CountDownLatch(size);
    for (int i = 0; i < size; i++) {
      final int res = i;
      CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
        try {
          TimeUnit.SECONDS.sleep(3);
          System.out.println(System.currentTimeMillis() + ":" + res + "执行完成");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return res + "";
      });
      f1.whenCompleteAsync((x, y) -> {
        try {
          TimeUnit.SECONDS.sleep(1);
          System.out.println(System.currentTimeMillis() + ":" + x + "回调执行完成");
          countDownLatch.countDown();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      comlist.add(f1);
    }

    CompletableFuture<Void> all = CompletableFuture.allOf(comlist.toArray(new CompletableFuture[size]));
    //阻塞，直到所有任务结束。任务complete就会执行,handler里面不一定会执行..
    System.out.println(System.currentTimeMillis() + ":阻塞");
    all.join();
    System.out.println(System.currentTimeMillis() + ":阻塞结束");
    countDownLatch.await();
    System.out.println("回调都结束...");
  }

  @Test
  public void testExp() throws Exception {
    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      throw new RuntimeException("测试异常");
    });

    f1.whenComplete((x, y) -> {
      System.out.println("whenComplete" + y.getMessage());
    });

    f1.exceptionally(t -> {
      System.out.println("exceptionally" + t.getMessage());
      return null;
    });
    System.out.println("end...");
    TimeUnit.SECONDS.sleep(3);
  }

  @Test
  public void allOfTest1() throws Exception {
    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "f1";
    });

    CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      throw new RuntimeException("aa");
//      return "f2";
    });

    CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2).handle((x, y) -> {
      System.out.println("all");
      return null;
    });

    //阻塞，直到所有任务结束。任务complete就会执行,handler里面不一定会执行..
    System.out.println(System.currentTimeMillis() + ":阻塞");
    //join 或者get
    Void aVoid = all.get();
    System.out.println(System.currentTimeMillis() + ":阻塞结束");

    //一个需要耗时2秒，一个需要耗时3秒，只有当最长的耗时3秒的完成后，才会结束。
    System.out.println("任务均已完成。");
  }

  @Test
  public void anyOfTest() throws Exception {
    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "f1";
    });

    CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
//      throw new RuntimeException("aa");
      return "f2";
    });
    CompletableFuture<Object> anyof = CompletableFuture.anyOf(f1, f2).handle((x, y) -> {
      System.out.println(x);
      return x;
    });
    ;

    //阻塞，直到所有任务结束。任务complete就会执行,handler里面不一定会执行..
    anyof.get();
    //一个需要耗时2秒，一个需要耗时3秒，只有当最长的耗时3秒的完成后，才会结束。
    System.out.println("任务均已完成。");

  }


  @Test
  public void allOfTest() throws Exception {
    CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "f1";
    });

    f1.whenCompleteAsync((x, y) -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(System.currentTimeMillis() + ":" + x + "执行完成");
    });

    CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      return "f2";
    });

    f1.whenCompleteAsync((x, y) -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(System.currentTimeMillis() + ":" + x + "执行完成");
    });

    CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2);

    //阻塞，直到所有任务结束。任务complete就会执行,handler里面不一定会执行..
    System.out.println(System.currentTimeMillis() + ":阻塞");
    all.join();
    System.out.println(System.currentTimeMillis() + ":阻塞结束");

    //一个需要耗时2秒，一个需要耗时3秒，只有当最长的耗时3秒的完成后，才会结束。
    System.out.println("任务均已完成。");
  }


  @Test
  public void testInt() throws Exception {

    int int1 = 0;
    Integer int2 = 1;
    testInt1(0);
    testInt1(new Integer(0));
    testInt1(int1);
    testInt1(int2);

    char a = 0;
    Character b = new Character(a);
    Character c = new Character('c');
    testChar1(a);
    testChar1(b);
    testChar1(c);

  }

  public void testInt1(Integer i) {
    System.out.println("integer");
  }

//  public void testInt1(int i) {
//    System.out.println("int");
//  }

//  public void testChar1(char i) {
//    System.out.println("char");
//  }

  public void testChar1(Character i) {
    System.out.println("Character");
  }

}
