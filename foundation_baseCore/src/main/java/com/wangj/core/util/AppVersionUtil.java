package com.wangj.core.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Android
 * @author WangJ jie581825@yeah.net 2018/4/4
 * @version TODO
 * Modified: TODO
 * Modified on 2018/4/4 by TODO
 */
public class AppVersionUtil {
    private static final String TAG = AppVersionUtil.class.getSimpleName();

    /**
     * 获取APP版本号
     * @param context 上下文
     * @return 如 1.2.3
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