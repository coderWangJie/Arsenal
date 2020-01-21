package com.wangj.core.util;

public class DeviceUtil {
    private static final String TAG = DeviceUtil.class.getSimpleName();

    public static void seeSystemInfo() {
        // 可用CPU
        LogUtil.d(TAG, "availableProcessors:" + Runtime.getRuntime().availableProcessors());
        // 进程内存情况
        LogUtil.d(TAG, "maxMemory(M):" + Runtime.getRuntime().maxMemory() / 1024 / 1024
                + "\ntotalMemory(M):" + Runtime.getRuntime().totalMemory() / 1024 / 1024
                + "\nfreeMemory(M):" + Runtime.getRuntime().freeMemory() / 1024 / 1024);
    }
}
