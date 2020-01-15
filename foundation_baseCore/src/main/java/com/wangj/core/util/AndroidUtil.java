package com.wangj.core.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * TODO
 *
 * @author WangJ jie581825@yeah.net 2018/4/4
 * @version TODO
 * Modified: TODO
 * Modified on 2018/4/4 by TODO
 */
public class AndroidUtil {
    private static final String TAG = AndroidUtil.class.getSimpleName();

    public static void seeSystemInfo() {
        // 可用CPU
        LogUtil.d(TAG, "availableProcessors:" + Runtime.getRuntime().availableProcessors());
        // 进程内存情况
        LogUtil.d(TAG, "maxMemory(M):" + Runtime.getRuntime().maxMemory() / 1024 / 1024
                + "\ntotalMemory(M):" + Runtime.getRuntime().totalMemory() / 1024 / 1024
                + "\nfreeMemory(M):" + Runtime.getRuntime().freeMemory() / 1024 / 1024);
    }

    /**
     * 获取APP版本号
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String var1 = "";

        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            if (packageInfo != null) {
                var1 = packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.e(TAG, "获取版本号失败：" + e.getMessage());
        }

        return var1;
    }
}