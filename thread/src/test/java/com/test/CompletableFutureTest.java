package com.test;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
      System.out.println(Thread.currentThread().getName() + "进行一连串操作1....");
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 1;
    }).thenApplyAsync(x -> {
      try {
        System.out.println(Thread.currentThread().getName() + "进行一连串操作2....");
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return x + 1;
    });
    CompletableFuture<Integer> future = future1.thenApplyAsync(x -> {
      try {
        System.out.println(Thread.currentThread().getName() + "进行一连串操作3....");
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
      System.out.println(Thread.currentThread().getName() + "进行一连串操作1....");
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 1;
    });
    future1.thenRun(() -> {
      System.out.println(Thread.currentThread().getName() + "进行一连串操作2....");
      countDownLatch.countDown();
    });
    countDownLatch.await();
    System.out.println(1);

  }

  @Test
  public void test3() throws Exception {
    CountDownLatch countDownLatch = new CountDownLatch(1);

    CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
      System.out.println(Thread.currentThread().getName() + "进行一连串操作1....");
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return 1;
    });

    //whenComplete方法,返回的future的结果还是1
    CompletableFuture<Integer> future = future1.whenComplete((x, y) -> {
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "whenComplete,future返回:" + x);
    });

    //handler返回的future结果是字符串"2"
    CompletableFuture<String> handle = future.handle((x, y) -> {
      System.out.println(Thread.currentThread().getName() + "handle接收的结果:" + x);
      countDownLatch.countDown();
      return "2";
    });
    CompletableFuture<Integer> handle1 = handle.handle((x, y) -> {
      System.out.println(Thread.currentThread().getName() + "handle返回的结果:" + x);
      countDownLatch.countDown();
      return 2;
    });
    countDownLatch.await();
    System.out.println(1);

  }

  @Test
  public void test41() throws Exception {
    Consumer<Integer> action1 = (x) -> {
      System.out.println("对传进来的进行加1操作: " + (x + 1));
    };
    Consumer<Integer> action2 = (x) -> {
      System.out.println("对传进来的进行减1操作: " + (x - 1));
    };
    Consumer<Integer> anction3 = action1.andThen(action2);

    //先执行加法在执行减法
    System.out.println("执行anction1");
    action1.accept(3);
    System.out.println("执行anction2");
    action2.accept(3);
    System.out.println("执行anction3");
    anction3.accept(3);

  }

  @Test
  public void test42() throws Exception {
    BiConsumer<Integer, Integer> action1 = (x, y) -> {
      System.out.println("对传进来的进行相加操作: " + (x + y));
    };
    BiConsumer<Integer, Integer> action2 = (x, y) -> {
      System.out.println("对传进来的进行相减操作: " + (x - y));
    };
    BiConsumer<Integer, Integer> anction3 = action1.andThen(action2);

    //先执行加法在执行减法
    System.out.println("执行anction1");
    action1.accept(1, 1);
    System.out.println("执行anction2");
    action2.accept(1, 1);
    System.out.println("执行anction3");
    anction3.accept(1, 1);

  }

  @Test
  public void test51() throws Exception {

    Function<Integer, Integer> action1 = (x) -> {
      System.out.println("对传进来的进行加1操作: " + (x + 1));
      return x + 1;
    };
    Function<Integer, Integer> action2 = (x) -> {
      System.out.println("对传进来的进行-1操作: " + (x - 1));
      return (x - 1);
    };
    Function<Integer, Integer> action3 = action1.andThen(action2);
    Function<Integer, Integer> compose = action1.compose(action2);

    //先执行加法在执行减法
    System.out.println("执行anction1");
    action1.apply(1);
    System.out.println("执行anction2");
    action2.apply(1);
    System.out.println("执行andThen返回的函数");
    action3.apply(1);
    System.out.println("执行compose返回的函数");
    compose.apply(1);

//Function.identity()返回一个输出跟输入一样的Lambda表达式对象，等价于形如t -> t形式的Lambda表达式
    Stream<String> stream = Stream.of("I", "love", "you", "too");
    //这三个相同的作用...就是返回的是原来的字符串.
//    Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));
    Map<String, Integer> map = stream.collect(Collectors.toMap(String::toString, String::length));
//    Map<String, Integer> map = stream.collect(Collectors.toMap(str->str, String::length));
    System.out.println(map);

  }

  @Test
  public void test52() throws Exception {
    BiFunction<Integer, Integer, Integer> action = (x, y) -> {
      System.out.println(x + y);
      return x + y;
    };
    Function<Integer, Integer> action2 = (x) -> {
      System.out.println("Function..." + x);
      return x;
    };
    BiFunction<Integer, Integer, Integer> integerIntegerBiConsumer = action.andThen(action2);

    integerIntegerBiConsumer.apply(1, 1);

  }

}
