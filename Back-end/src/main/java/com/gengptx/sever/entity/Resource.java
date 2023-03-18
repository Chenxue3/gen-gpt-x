package com.gengptx.sever.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：xueshanChen
 * @ClassName : Resource
 * @description：
 * @version: v1.0
 */

public class Resource {
    private MultipartFile data;
    private String path;

    public MultipartFile getData() {
        return data;
    }

    public void setData(MultipartFile data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "data=" + data +
                ", path='" + path + '\'' +
                '}';
    }
}
