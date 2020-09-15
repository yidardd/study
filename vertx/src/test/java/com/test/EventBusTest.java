package com.test;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-27 15:58
 * @Version 1.0
 * @Description CompleteFutureTest
 */
@RunWith(VertxUnitRunner.class)
public class EventBusTest {

  public static final Vertx vertx = Vertx.vertx();

  @Test
  public void test1(TestContext testContext) throws Exception {
    Async async = testContext.async();
    EventBus eb = vertx.eventBus();

    MessageConsumer<Integer> aaa = eb.consumer("aaa");
    aaa.handler(x -> {
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("接收的数字是:" + x.body());

      x.reply(x.body() + 1, ar -> {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (ar.succeeded()) {
          Message<Object> result1 = ar.result();
          System.out.println("接收的数字是:" + result1.body());
          result1.reply(Integer.valueOf(result1.body().toString()) + 1);
        }
      });
    });


    eb.send("aaa", 1, ar -> {
      if (ar.succeeded()) {
        Message<Object> result = ar.result();
        result.reply(result.body(), ar1 -> {
            Message<Object> result1 = ar1.result();
            System.out.println("接收的数字是:" + result1.body());
            result1.reply(Integer.valueOf(result1.body().toString()) + 1);
        });
      }
    });


  }

  @Test
  public void test2(TestContext testContext) throws Exception {
    Async async = testContext.async();
    EventBus eb = vertx.eventBus();
    TimeUnit.SECONDS.sleep(1);
    MessageConsumer<Object> aaa = eb.consumer("aaa");
    aaa.handler(x -> {
      System.out.println(x.body());
    });
  }

}
