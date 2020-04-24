package com.study.www;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class EchoServer extends AbstractVerticle {
 
    @Override
    public void start() {
        System.out.println("HttpServer star....");
        HttpServerOptions httpServerOptions = new HttpServerOptions();
        httpServerOptions.setMaxInitialLineLength(1024 * 1024*1024).setMaxHeaderSize(1024 * 1024*1024);
        HttpServer httpServer = getVertx().createHttpServer(httpServerOptions);
        Router router = Router.router(vertx);
 
        //异步 route里的是自定义路径
        router.route("/hello2").handler(new Handler<RoutingContext>() {
            public void handle(RoutingContext routingContext) {
                HttpServerRequest request = routingContext.request();
                HttpServerResponse response = routingContext.response();
                String name = request.getParam("name");
            
                getVertx().executeBlocking(f -> {
                    byte[] shetfList = ("沙发大师傅,"+name).getBytes();
                    response.putHeader("content-type", "text/plain");
                    response.putHeader("text/html", "charset=UTF-8");
//                    response.putHeader("content-encoding", "gzip");
                    response.putHeader("content-length", String.valueOf(shetfList.length));
                    response.write(Buffer.buffer(shetfList));
                    response.setStatusCode(200);
                    response.end();
                }, false, r -> {
                });
            }
        });
 
        router.get("/hello").handler(new Handler<RoutingContext>() {
            public void handle(RoutingContext routingContext) {
                routingContext.response().putHeader("content-type", "text/html").end("Hello World");
            }
        });
 
        //异步
        router.route("/server/status").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            getVertx().executeBlocking(f -> {
                response.putHeader("content-type", "text/plain");
                response.putHeader("text/html", "charset=UTF-8");
                response.setStatusCode(200);
                response.end(Buffer.buffer("I AM OK!"));
            }, false, r -> {
            });
        });
 
        //使用阻塞处理程序
        router.route().blockingHandler(routingContext -> {
            // Do something that might take some time synchronously
            //执行下一个方法
            routingContext.next();
//            HttpServerResponse response = routingContext.response();
//            vertx.executeBlocking(f -> {
//                response.putHeader("content-type", "text/plain");
//                response.putHeader("text/html", "charset=UTF-8");
//                response.setStatusCode(200);
//                response.end(Buffer.buffer("路径出错!"));
//            }, false, r -> {
//            });
        });
 
        // 传递方法引用，监听端口
        getVertx().createHttpServer().requestHandler(router::accept).listen(9090);
        System.out.println("HttpServer start over");
    }
 
    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        options.setWorkerPoolSize(200);
        options.setEventLoopPoolSize(300);
        options.setInternalBlockingPoolSize(200);
        // Vert.x实例是vert.x api的入口点，我们调用vert.x中的核心服务时，均要先获取vert.x实例，
        // 通过该实例来调用相应的服务，例如部署verticle、创建http server
        Vertx.vertx(options).deployVerticle(new EchoServer());
 
    }
 
}