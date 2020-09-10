package com.zfylin.study.java.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过指定JVM参数：-Xmx8m -Xms8m 模拟OOM异常
 *
 * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 * 	at com.zfylin.study.java.executor.ExecutorsDemo.main(ExecutorsDemo.java:17)
 *
 * 参考链接: https://developer.aliyun.com/article/756612
 *
 * @author zfylin
 * @version 2020/09/10
 */
@SuppressWarnings("all")
public class ExecutorsDemo {
    private static ExecutorService executor = Executors.newFixedThreadPool(15);
    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor.execute(new SubTask());
        }
    }
}
