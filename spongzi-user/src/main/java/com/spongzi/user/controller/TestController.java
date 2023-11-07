package com.spongzi.user.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 测试控制器
 *
 * @author spong
 * @date 2023/11/06
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
