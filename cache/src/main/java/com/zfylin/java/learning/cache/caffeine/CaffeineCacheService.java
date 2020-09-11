package com.zfylin.java.learning.cache.caffeine;

import cn.hutool.http.HttpUtil;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zfylin
 * @version 2020/09/10
 */
public class CaffeineCacheService {
    private static LoadingCache<String, Optional<String>> cache = Caffeine
            .newBuilder()
            // 设置缓存的 Entries 个数最多不超过1000个
            .maximumSize(1000)
            // 定时刷新
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            .recordStats()
            // 异步加载
            .build(s -> Optional.ofNullable(HttpUtil.get(s)));

    public static Optional<String> get(String url) {
        return cache.get(url);
    }

    public static CacheStats getCacheStats() {
        return cache.stats();
    }
}
