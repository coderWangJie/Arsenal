package com.wangj.core.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.wangj.core.android.BaseApplication;
import com.wangj.core.constant.SharedPreferenceKey;

import java.util.Set;

/**
 * SharedPreference工具类<br>
 * 建议将键名定义在{@link SharedPreferenceKey}中，避免在代码中直接随处起名导致混乱难以管理
 *
 * @author WangJ email:jie581825@yeah.net
 * @version 1.0
 * <p>
 * if you modify some, PLEASE RECORD YOUR MODIFY !!!
 * @since 2019/4/8 20:59
 */
public class SharedPreferenceUtil {

    private static SharedPreferences sharedPreferences;

    static {
        sharedPreferences = BaseApplication.getAppContext()
                .getSharedPreferences("AppSharedPrf", Context.MODE_PRIVATE);
    }

    public static void setInfoToShared(String name, String newValue) {
        sharedPreferences.edit().putString(name, newValue).apply();
    }

    public static String getString(String name) {
        return sharedPreferences.getString(name, "");
    }

    public static String getString(String name, String defValue) {
        return sharedPreferences.getString(name, defValue);
    }

    public static void setInfoToShared(String name, Set<String> newValue) {
        sharedPreferences.edit()
                .putStringSet(name, newValue)
                .apply();
    }

    public static Set<String> getStringSet(String name, Set<String> defValue) {
        return sharedPreferences.getStringSet(name, defValue);
    }

    public static void setInfoToShared(String name, boolean newValue) {
        sharedPreferences.edit().putBoolean(name, newValue).apply();
    }

    public static boolean getBoolean(String name, boolean defValue) {
        return sharedPreferences.getBoolean(name, defValue);
    }

    public static void setInfoToShared(String name, int newValue) {
        sharedPreferences.edit()
                .putInt(name, newValue)
                .apply();
    }

    public static int getInt(String name, int defValue) {
        return sharedPreferences.getInt(name, defValue);
    }

    public static void setInfoToShared(String name, float newValue) {
        sharedPreferences.edit()
                .putFloat(name, newValue)
                .apply();
    }

    public static float getFloat(String name, float defValue) {
        return sharedPreferences.getFloat(name, defValue);
    }

    public static void setInfoToShared(String name, long newValue) {
        sharedPreferences.edit()
                .putLong(name, newValue)
                .apply();
    }

    public static long getLong(String name, long defValue) {
        return sharedPreferences.getLong(name, defValue);
    }
}
