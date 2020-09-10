package com.zfylin.study.java.executor;

/**
 * @author zfylin
 * @version 2020/09/10
 */
public class SubTask implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            //do nothing
        }
    }
}
