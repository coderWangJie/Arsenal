package com.wangj.arsenal;

import android.content.res.Configuration;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wangj.core.android.BaseApplication;
import com.wangj.core.util.LogUtil;

public class ProjectApplication extends BaseApplication {
    private static final String TAG = ProjectApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        // ARouter初始化
        if (BuildConfig.DEBUG) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

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

    @Override
    public void onTerminate() {
        super.onTerminate();
        // ARouter释放
        ARouter.getInstance().destroy();
    }
}
