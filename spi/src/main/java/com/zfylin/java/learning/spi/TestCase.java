package com.zfylin.java.learning.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author zfylin
 * @version 2020/09/11
 */
public class TestCase {
    public static void main(String[] args) {
        ServiceLoader<ISearch> s = ServiceLoader.load(ISearch.class);
        Iterator<ISearch> iterator = s.iterator();
        while (iterator.hasNext()) {
            ISearch search =  iterator.next();
            search.searchDoc("hello world");
        }
    }
}
