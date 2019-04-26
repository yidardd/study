package com.test;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 东东 on 2019/3/28.
 */
public class MainTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

            System.out.println("开始执行-----");
            Future<String> future = Executors.newCachedThreadPool().submit(() -> {
                HttpRequest get = HttpUtil.createGet("http://192.168.110.47:8082/single-h5/consult/getGraphCode");
                HttpResponse execute = get.execute();

                System.out.println("执行完毕----");
                return "success";
            });
    }
}
    