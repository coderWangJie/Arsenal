package com.wangj.core.android;

import android.app.Application;

public abstract class BaseApplication extends Application {

    private static Application mInstant;

    public static Application getAppContext() {
        return mInstant;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstant = this;
    }
}
