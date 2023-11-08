package com.spongzi.user.controller;

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
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/testRedis")
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("name", "zhangsan");
    }
}
