package com.spongzi.redis.util;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 实用程序实用程序缓存
 *
 * @author spong
 * @date 2023/11/09
 */
@Slf4j
@Component
public class CacheUtil<K, V> {

    @Value("${guava.cache.switch}")
    public Boolean switchCache;

    // 初始化本地缓存
    private final Cache<String, String> localCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterAccess(3, TimeUnit.SECONDS)
            .build();

    /**
     * 获取结果
     *
     * @param skuIdList   SKU ID列表
     * @param cachePrefix 高速缓存前缀
     * @param clazz       克拉兹
     * @param function    功能
     * @return {@link Map}<{@link K}, {@link V}>
     */
    public Map<K, V> getResult(List<K> skuIdList,
                               String cachePrefix,
                               Class<V> clazz,
                               Function<List<K>, Map<K, V>> function) {
        // 参数校验
        if (CollectionUtils.isEmpty(skuIdList)) {
            return Collections.emptyMap();
        }
        // 生成一个结果
        Map<K, V> resultMap = new HashMap<>(16);
        // 判断开关
        if (!switchCache) {
            resultMap = function.apply(skuIdList);
            return resultMap;
        }
        // 声明本地缓存没有的list
        List<K> noCacheIdList = new LinkedList<>();
        // 开始遍历
        for (K id : skuIdList) {
            String cacheKey = cachePrefix + id;
            String content = localCache.getIfPresent(cacheKey);
            if (StringUtils.isNotBlank(content)) {
                V v = JSON.parseObject(content, clazz);
                resultMap.put(id, v);
            } else {
                noCacheIdList.add(id);
            }
        }
        if (CollectionUtils.isEmpty(noCacheIdList)) {
            return resultMap;
        }
        Map<K, V> noCacheResultMap = function.apply(noCacheIdList);
        if (noCacheResultMap == null || noCacheResultMap.isEmpty()) {
            return resultMap;
        }
        for (Map.Entry<K, V> entry : noCacheResultMap.entrySet()) {
            K id = entry.getKey();
            V result = entry.getValue();
            resultMap.put(id, result);
            String cacheKey = cachePrefix + id;
            localCache.put(cacheKey, JSON.toJSONString(result));
        }
        return resultMap;
    }
}
