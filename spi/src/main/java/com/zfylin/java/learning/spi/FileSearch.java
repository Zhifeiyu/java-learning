package com.zfylin.java.learning.spi;

import java.util.List;

/**
 * @author zfylin
 * @version 2020/09/11
 */
public class FileSearch implements ISearch {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索 " + keyword);
        return null;
    }
}
