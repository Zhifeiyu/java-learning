package com.zfylin.java.learning.cache.guava;

import cn.hutool.http.HttpUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zfylin
 * @version 2020/09/10
 */
@Slf4j
public class CacheService {

    private static RemovalListener<String, Optional<String>> removalListener =
            removal -> log.info("remove {}-> {}", removal.getKey(), removal.getValue());

    private static LoadingCache<String, Optional<String>> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            // 定时过期
            //  .expireAfterWrite(3, TimeUnit.SECONDS)
            // 定时刷新
            .refreshAfterWrite(3, TimeUnit.SECONDS)
            .recordStats()
            .removalListener(removalListener)
            .build(new RefreshAsyncCacheLoader<String, Optional<String>>() {
                @Override
                public Optional<String> load(String url) {
                    log.info("load from {}", url);
                    return Optional.ofNullable(HttpUtil.get(url));
                }
            });

    public static Optional<String> get(String url) throws ExecutionException {
        return cache.get(url);
    }

    public static CacheStats getCacheStats() {
        return cache.stats();
    }
}
