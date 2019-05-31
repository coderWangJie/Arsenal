package com.wangj.core.util;

import android.content.Context;

/**
 * View相关工具
 * Created by WangJ on 2017/7/25.
 */
public class ViewUtil {
    /**
     * 获取sp、dp对应的px值
     *
     * @param context 上下文
     * @param dpValue dp、sp的值
     * @return 转换后的px值
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (dpValue * scale + 0.5f);
    }
}
