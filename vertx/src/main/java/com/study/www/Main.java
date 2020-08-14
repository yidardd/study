package com.study.www;

import io.vertx.core.Vertx;

/**
 * @author <a href="mailto:15268179013@139.com">yida</a>
 * @Version 2020-04-12 19:06
 * @Version 1.0
 * @Description Main
 */
public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(EchoServer.class.getName());
    }


}
