package com.wangj.demo.model;

import android.support.v4.app.Fragment;

import com.wangj.core.entity.BaseVO;

public class TabViewPagerVO extends BaseVO {

    private String title;
    private Fragment fragment;

    public TabViewPagerVO(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public Fragment getFragment() {
        return fragment;
    }
}
