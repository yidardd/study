package com.study.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
     * 当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
     * 当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个请求
     */
//    private RateLimiter r = RateLimiter.create(1);

    /**
     * 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
     * 还包含一个热身期(warmup period),热身期内，RateLimiter会平滑的将其释放令牌的速率加大，直到起达到最大速率
     * 同样，如果RateLimiter在热身期没有足够的请求(unused),则起速率会逐渐降低到冷却状态
     *
     * 设计这个的意图是为了满足那种资源提供方需要热身时间，而不是每次访问都能提供稳定速率的服务的情况(比如带缓存服务，需要定期刷新缓存的)
     * 参数warmupPeriod和unit决定了其从冷却状态到达最大速率的时间
     */
    private RateLimiter r = RateLimiter.create(1, 2, TimeUnit.SECONDS);

    @GetMapping("/rateLimiter")
    public String rateLimiter() {
        //尝试获取permits个令牌,带超时时间
//        boolean b = r.tryAcquire(3,TimeUnit.SECONDS);
        //尝试获取permits个令牌,不带超时时间，直接返回的
        boolean b = r.tryAcquire();
        if (!b) {
            return "限流了..";
        }
        return "欢迎光临";
    }

    @GetMapping("/")
    public String index() {
        return "欢迎光临";
    }

    @GetMapping("/testLock/{userId}")
    public String testLock(@PathVariable("userId") Integer userId) {
        //每个用户 每五秒请求一次
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(userId + "", "das", 3600L, TimeUnit.SECONDS);
        if (!aBoolean) {
            return "请求速度太快了...";
        }
        return "欢迎光临";
    }




}
