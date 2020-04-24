package com.test;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.test.core.TestVerticle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-19 14:09
 * @Version 1.0
 * @Description VertxTest
 */
@RunWith(VertxUnitRunner.class)
public class VertxTest {


  private Vertx vertx;
  private FileSystem fs;


  // @Rule
  // public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();
    fs = vertx.fileSystem();
    vertx.deployVerticle(new TestVerticle());
  }

  @After
  public void after(TestContext context) {
    System.out.println("after  ...");
    vertx.close(context.asyncAssertSuccess());
  }

  private void asynchronousMethod(Future<String> f) {
    CompletableFuture.runAsync(() -> {
    System.out.println("asynchronousMethod...");
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    f.complete("haha");
    });
  }

  private void synchronousMethod(Future<String> f) {
    CompletableFuture.runAsync(() -> {
      System.out.println("asynchronousMethod...");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      f.complete("haha");
    });
  }

  @Test
  public void testsetHandler(TestContext context) {
    Async async = context.async();

    Future<String> future = Future.future();
    future.setHandler(r -> {
      System.out.println("result is:" + r);
      async.complete();
    });

      asynchronousMethod(future);

    System.out.println(1);
  }

  @Test
  public void testCompose(TestContext context) {
    Async async = context.async();
    Future<String> future = Future.future();
    future.setHandler(r -> {
      System.out.println("result is:" + r);
      async.complete();
    });
    Future<String> future2 = Future.future();

    future.compose(x->{

    }, future2);


    asynchronousMethod(future);

    System.out.println(1);
  }

  @Test
  public void test2(TestContext context) {
    Async async = context.async();

    vertx.executeBlocking(future -> {
      // 调用一些需要耗费显著执行时间返回结果的阻塞式API
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      future.complete("complete...over...");
    },res -> {
      System.out.println("The result is: " + res.result());
      async.complete();
    });

  }


}
