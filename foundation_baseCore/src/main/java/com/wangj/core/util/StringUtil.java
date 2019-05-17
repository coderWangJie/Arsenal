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


}
