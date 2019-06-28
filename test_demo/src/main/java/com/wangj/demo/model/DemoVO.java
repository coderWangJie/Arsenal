package com.wangj.demo.model;

import com.wangj.core.entity.BaseVO;

public class DemoVO extends BaseVO {
    /** ARouter路径，用于路由跳转 */
    private String path;
    /** 名称，用于展示 */
    private String name;

    public DemoVO(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
