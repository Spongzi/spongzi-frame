package com.spongzi.redis.util;

import com.spongzi.redis.exception.ShareLockException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis共享锁定实用程序
 *
 * @author spong
 * @date 2023/11/08
 */
@Component
public class RedisShareLockUtil {

    @Resource
    private RedisUtil redisUtil;

    private static final Long TIME_OUT = 100L;

    /**
     * 加锁
     *
     * @param lockKey   锁键
     * @param requestId 请求id
     * @return {@link Boolean}
     */
    public Boolean lock(String lockKey, String requestId, Long time) {
        // 1、参数校验
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(requestId) || time <= 0) {
            throw new ShareLockException("分布式锁-加锁参数错误");
        }
        long currentTime = System.currentTimeMillis();
        long outTime = currentTime + TIME_OUT;
        boolean result;
        // 2、加锁可以自旋
        while (currentTime < outTime) {
            // 3、借助redis的setnx来进行锁的设置
            result = redisUtil.setNx(lockKey, requestId, time, TimeUnit.MILLISECONDS);
            if (!result) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return true;
            }
            currentTime = System.currentTimeMillis();
        }
        return false;
    }

    /**
     * 解锁
     *
     * @return {@link Boolean}
     */
    public Boolean unLock(String key, String requestId) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(requestId)) {
            throw new ShareLockException("分布式锁-解锁参数错误");
        }
        try {
            String value = redisUtil.get(key);
            if (requestId.equals(value)) {
                boolean del = redisUtil.del(key);
                return true;
            }
        } catch (Exception e) {
            // 补日志
        }
        return false;
    }

    /**
     * 尝试加锁
     *
     * @return {@link Boolean}
     */
    public Boolean tryLock(String lockKey, String requestId, Long time) {
        // 1、参数校验
        if (StringUtils.isBlank(lockKey) || StringUtils.isBlank(requestId) || time <= 0) {
            throw new ShareLockException("分布式锁-尝试加锁参数错误");
        }
        long currentTime = System.currentTimeMillis();
        long outTime = currentTime + TIME_OUT;
        // 2、加锁可以自旋
        // 3、借助redis的setnx来进行锁的设置
        return redisUtil.setNx(lockKey, requestId, time, TimeUnit.SECONDS);
    }
}
