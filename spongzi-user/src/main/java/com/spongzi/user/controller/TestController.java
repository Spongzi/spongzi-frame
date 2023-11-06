package com.spongzi.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
