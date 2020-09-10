package com.zfylin.study.java.cache.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步刷新缓存 CacheLoader
 *
 * @author zfylin
 * @version 2020/09/10
 */
@Slf4j
@SuppressWarnings("all")
public abstract class RefreshAsyncCacheLoader<K, V> implements CacheLoader<K, V> {
    private static ThreadFactory nameFactory = new ThreadFactoryBuilder()
            .setNameFormat("refresh-async-cache-loader").build();

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10,
            10, 60, TimeUnit.SECONDS, new
            ArrayBlockingQueue<>(10), nameFactory, new ThreadPoolExecutor.AbortPolicy());


    @Override
    public V reload(final K key, final V oldValue) {
        try {
            V val = load(key);
            log.debug("reload key[{}], new value: {}", key, val);
            return val;
        } catch (Exception e) {
            // 异常返回旧的value
            log.error("load key[{}] error, return old value: {}, error: {}", key, oldValue, e.getMessage());
            return oldValue;
        }
    }

}
