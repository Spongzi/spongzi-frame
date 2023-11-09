package com.spongzi.user.cache;

import com.spongzi.redis.init.AbstractCache;
import com.spongzi.redis.util.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SysUserCache extends AbstractCache {

    public static final String SYS_USER_CACHE_KEY = "user";

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void initCache() {
        // 跟数据来源进行联动
        redisUtil.set("age", "18");
    }

    @Override
    public <T> T getCache(String key) {
        if (!redisUtil.exist(key)) {
            reloadCache();
        }
        return (T) redisUtil.get(key);
    }

    @Override
    public void clearCache() {
        redisUtil.del(SYS_USER_CACHE_KEY);
    }
}
