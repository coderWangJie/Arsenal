package com.wangj.arsenal;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.wangj.fun.util.LogUtil;

public class ProjectApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 设置LogUtil工具打印日志级别
        LogUtil.setLogLevel(Log.DEBUG);
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
