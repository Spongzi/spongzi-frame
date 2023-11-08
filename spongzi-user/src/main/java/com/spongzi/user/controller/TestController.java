package com.spongzi.user.controller;

import com.spongzi.redis.util.RedisUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 测试控制器
 *
 * @author spong
 * @date 2023/11/06
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/testRedis")
    public void testRedis(){
        redisUtil.set("name", "zhangsan");
    }
}
