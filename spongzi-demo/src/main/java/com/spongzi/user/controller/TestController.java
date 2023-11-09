package com.spongzi.user.controller;

import com.spongzi.redis.util.RedisShareLockUtil;
import com.spongzi.redis.util.RedisUtil;
import com.spongzi.tool.util.ExportWordUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

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

    @Resource
    private RedisShareLockUtil redisShareLockUtil;

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/testRedis")
    public void testRedis(){
        redisUtil.set("name", "zhangsan");
    }

    @GetMapping("/testRedisLock")
    public void testRedisLock(){
        Boolean result = redisShareLockUtil.lock("spongzi", "123456", 10000L);
        System.out.println(result);
    }

    @GetMapping("/testExport")
    public void testExport() throws Exception {
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "spongzi");
        dataMap.put("auditName", "keleSpongzi");
        ExportWordUtil.exportWord(dataMap, "导出文件", "wordExport.ftl");
    }
}
