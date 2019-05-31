package com.wangj.core.util;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class StringUtil {
    private static String TAG = StringUtil.class.getSimpleName();

    /**
     * is not null or .<br>
     * if you want a method like 'StringUtil.isEmpty()', please use {@link TextUtils#isEmpty(CharSequence)}.
     */
    public static boolean isNotEmpty(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence);
    }

    /**
     * 简单JsonObject的字符串转成HashMap
     * @param jsonStr {@link String}
     * @return {@link HashMap}
     */
    public static HashMap<String, String> parseStr2Map(String jsonStr){
        HashMap<String, String> map = new HashMap<>();
        try {
            JSONObject json = new JSONObject(jsonStr);
            Iterator<?> it = json.keys();
            // 遍历jsonObject数据，添加到Map对象
            while (it.hasNext()) {
                String key = it.next().toString();
                String value = json.optString(key);
                map.put(key, value);
            }
        } catch (JSONException e) {
            LogUtil.e(TAG, "parseStr2Map()方法Json字符串解析成Map结构出错！");
        }
        return map;
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
