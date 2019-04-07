package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by 东东 on 2019/3/14.
 */
@RestController
public class IndexController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/")
    public String index() {
        return "欢迎光临";
    }

    @GetMapping("/testLock/{userId}")
    public String testLock(@PathVariable("userId") Integer userId) {
        //每个用户 每五秒请求一次
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(userId+"", "das",3600L, TimeUnit.SECONDS);
        if (!aBoolean) {
            return "请求速度太快了...";
        }
        return "欢迎光临";
    }

}
