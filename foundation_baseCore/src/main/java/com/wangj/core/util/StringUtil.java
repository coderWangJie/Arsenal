package com.wangj.core.util;

import android.text.TextUtils;

public class StringUtil {

    /**
     * is not null or .<br>
     * if you want a method like 'StringUtil.isEmpty()', please use {@link TextUtils#isEmpty(CharSequence)}.
     */
    public static boolean isNotEmpty(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence);
    }

    /**
     * TODO 判断是否全是小写
     *
     * @param charSequence
     * @return
     */
    public static boolean isAllLowerCase(CharSequence charSequence) {
        return false;
    }

    /**
     * TODO 判断是否全是大写
     * @param charSequence
     * @return
     */
    public static boolean isAllUpperCase(CharSequence charSequence) {
        return false;
    }
}
