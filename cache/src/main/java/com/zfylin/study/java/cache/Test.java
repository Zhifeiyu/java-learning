package com.zfylin.study.java.cache;

import cn.hutool.core.thread.ThreadUtil;
import com.zfylin.study.java.cache.guava.CacheService;

import java.util.concurrent.ExecutionException;

/**
 * @author zfylin
 * @version 2020/09/10
 */
public class Test {
    public static void main(String[] args) throws ExecutionException {
        System.out.println(CacheService.get("http://localhost:8888/"));
        ThreadUtil.sleep(3 * 1000);
        System.out.println(CacheService.get("http://localhost:8888/"));
        ThreadUtil.sleep(10 * 1000);

        System.out.println(CacheService.get("http://localhost:8888/"));

        ThreadUtil.sleep(3 * 1000);
        System.out.println(CacheService.get("http://localhost:8888/"));
        ThreadUtil.sleep(3 * 1000);
        System.out.println(CacheService.get("http://localhost:8888/"));


        System.out.println("\n\n============= stats ================");
        System.out.println(CacheService.getCacheStats());

        while (true) {

        }
    }
}
