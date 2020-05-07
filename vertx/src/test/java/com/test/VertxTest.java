package com.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

  private void asynchronousMethod(Future<String> f) {
    CompletableFuture.runAsync(() -> {
      System.out.println("asynchronousMethod...");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
//      f.complete("haha");
      f.fail(new NullPointerException("aa"));
    });
  }

  @Test
  public void testsetHandler(TestContext context) {
    Async async = context.async();
    Future<String> future = Future.future();
    future.setHandler(r -> {
      if (r.succeeded()) {
        //成功执行
        String result = r.result();
        System.out.println("result is:" + result);
      } else {
        //失败执行
        Throwable cause = r.cause();
        System.out.println("异常:" + cause);
      }
      async.complete();
    });
    //异步执行其他操作,操作完成执行future.complete方法.
    asynchronousMethod(future);
    System.out.println("handler设置完成...");
  }

  @Test
  public void testTryComplete(TestContext context) {
    Async async = context.async();
    Future<String> future = Future.future();
    future.setHandler(r -> {
      if (r.succeeded()) {
        //成功执行
        String result = r.result();
        System.out.println("result is:" + result);
      } else {
        //失败执行
        Throwable cause = r.cause();
        System.out.println("异常:" + cause);
      }
      async.complete();
    });
    //异步执行其他操作,操作完成执行future.complete方法.
    asynchronousMethod(future);
    System.out.println("handler设置完成...");
    //不进行sleep,返回的就是ccc
//    try {
//      TimeUnit.SECONDS.sleep(3);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    boolean b = future.tryComplete("ccc");
    System.out.println(b);

  }

  @Test
  public void testMap(TestContext context) {
    Async async = context.async();
    Future<String> future = Future.future();
    future.setHandler(r -> {
      if (r.succeeded()) {
        //成功执行
        String result = r.result();
        System.out.println("result is:" + result);
      } else {
        //失败执行
        Throwable cause = r.cause();
        System.out.println("异常:" + cause);
      }
      async.complete();
    });
    //异步执行其他操作,操作完成执行future.complete方法.
    asynchronousMethod(future);
    System.out.println("handler设置完成...");
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Future<Object> objectFuture = future.mapEmpty();

    System.out.println(objectFuture);
    Future<String> map = future.map(x -> {
      System.out.println(x);
      return "fuction...return" + x;
    });
    map.setHandler(x -> {
      System.out.println("map result :" + x.result());
      async.complete();
    });
  }

  //  public static void handleAddCollection(RoutingContext routingContext){
//    Future<Void> futn=Future.future();
//    futn.setHandler(a->{
//      System.out.println("4最后一步：");
//      sendData(routingContext,"ok");
//      return;
//    });
//    Future<UpdateResult> fut1 = Future.future();
//    JsonArray params=new JsonArray().add("123").add("测试标题").add("http://baidu.com").add("");
//    String sql="insert into mytable (userid,title,url,pic) values (?,?,?,?)";
//    myDBConnecton.updateWithParams(sql,params,fut1.completer());
//    fut1.compose(v-> {
//      if(v.getUpdated()>0)System.out.println("2 插入成功了");
//      else System.out.println("2 插入失败了");
//      Future<ResultSet> fut2 = Future.future();
//      myDBConnecton.query("select * from mytable", fut2.completer());
//      return fut2;
//    }).compose(v -> {
//      List<JsonObject> data=v.getRows();
//      System.out.println("3查询结果为："+data.toString());
//      Future<Void> fut3 = Future.future();
//      myDBConnecton.updateWithParams(sql,params,fut1.completer());
//      futn.complete();
//    },futn);
//  }
  @Test
  public void testCompose(TestContext context) {
    Future<String> f1 = Future.future();
    Future<Integer> f2 = Future.future();

    f1.complete("f1's result");

//    f1.setHandler(x -> {
//      System.out.println("f1 handler:" + x);
//      f2.complete(123);
//    });
//    f2.setHandler(x -> {
//      System.out.println("f2 handler:" + x);
//    });

    f1.compose(r -> {
      System.out.println("f1 handler:" + r);
      f2.complete(123);
    }, f2).setHandler(r -> {
      System.out.println("f2 handler:" + r.result());
    });
  }

  @Test
  public void testCompose2(TestContext context) {
    Future<String> f1 = Future.future();
    f1.complete("f1's result");
    f1.compose(r -> {
      System.out.println("f1  hander :"+r);
      Future<String> f2 = Future.future();
      f2.complete("f2's result");
      //返回的f2,下一个componse的执行者
      return f2;
    }).compose(r -> {
      System.out.println("f2  hander :"+r);
      Future<String> f3 = Future.future();
      f3.complete("f3's result");
      //返回的f3,setHandler
      return f3;
    }).setHandler(r -> {
      System.out.println("f3  hander :"+r);
    });

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
    }, res -> {
      System.out.println("The result is: " + res.result());
      async.complete();
    });

  }


}
