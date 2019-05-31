package com.wangj.core.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 和货币金额先关的工具类
 * Created by WangJ on 2017/8/9.
 */
public class MoneyUtil {
    /* 由于TAG要在静态方法中使用，只能在每个工具类中设置TAG值 */
    private static final String TAG = "MoneyUtil";

    /**
     * 将double类型的金额###,##0.00格式化，用于显示
     *
     * @param value 金额的double值
     * @return 格式化金额字符串（形如10,000.00）
     */
    public static String formatMoney2Show(double value) {
        BigDecimal amount = new BigDecimal(value);
        // #和0都是占位符，0作占位符时若遇空位则补0，#做占位符空位不处理
        DecimalFormat format = new DecimalFormat("###,##0.00");
        return format.format(amount);
    }

    /**
     * 将String显示的金额###,##0.00格式化，用于显示
     *
     * @param moneyStr 金额字符串
     * @return 格式化后的金额字符串，形如1,000.00，输入为空则返回"--"
     */
    public static String formatMoney2Show(String moneyStr) {
        if (TextUtils.isEmpty(moneyStr)) {
            LogUtil.e(TAG, "the parameter of method formatMoney2Show(String moneyStr) is null");
            return null;
        }

        BigDecimal amount = new BigDecimal(moneyStr.replace(",", ""));
        // #和0都是占位符，0作占位符时若遇空位则补0，#做占位符空位不处理
        DecimalFormat format = new DecimalFormat("###,##0.00");
        return format.format(amount);
    }

    /**
     * 金额###0.00格式化，用于网络传输
     *
     * @param moneyStr 金额字符串
     * @return ####0.00格式化后的字符串
     */
    public static String formatMoney2Trans(String moneyStr) {
        if (TextUtils.isEmpty(moneyStr)) {
            LogUtil.e(TAG, "the parameter of method formatMoney2Trans(String moneyStr) is null");
            return null;
        }

        BigDecimal amount = new BigDecimal(moneyStr.replace(",", ""));
        DecimalFormat format = new DecimalFormat("####0.00");
        return format.format(amount);
    }

    /**
     * 金额###0.00格式化，用于网络传输
     *
     * @param value 金额
     * @return ####0.00格式结果字符串
     */
    public static String formatMoney2Trans(double value) {
        BigDecimal amount = new BigDecimal(value);
        // #和0都是占位符，0作占位符时若遇空位则补0，#做占位符空位不处理
        DecimalFormat format = new DecimalFormat("####0.00");
        return format.format(amount);
    }

    /**
     * 获取金额字符串的double值
     *
     * @param stringStr 字符串
     * @return double值
     */
    public static double getMoneyValue(String stringStr) {
        if (TextUtils.isEmpty(stringStr)) {
            LogUtil.e(TAG, "the money String is null or ''");
            return 0;
        }

        stringStr = stringStr.replace(",", "");
        return Double.parseDouble(stringStr);
    }
}
