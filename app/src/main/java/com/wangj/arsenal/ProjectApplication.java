package com.wangj.arsenal;

import android.content.res.Configuration;
import android.util.Log;

import com.wangj.core.android.BaseApplication;
import com.wangj.core.util.LogUtil;

public class ProjectApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        // 设置LogUtil工具打印日志级别
        LogUtil.setLogLevel(BuildConfig.showLogLevel);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
