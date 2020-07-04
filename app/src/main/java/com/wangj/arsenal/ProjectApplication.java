package com.wangj.arsenal;

import android.content.res.Configuration;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wangj.core.android.BaseApplication;
import com.wangj.core.constant.BuildConstant;
import com.wangj.core.util.LogUtil;

public class ProjectApplication extends BaseApplication {
    private static final String TAG = ProjectApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "BuildConstant.isDebug=" + BuildConstant.isDebug, Toast.LENGTH_LONG).show();

        // ARouter初始化
        if (BuildConstant.isDebug) {
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
