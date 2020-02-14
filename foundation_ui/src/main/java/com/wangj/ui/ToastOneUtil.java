package com.wangj.ui;

import android.content.Context;
import android.widget.Toast;

@Deprecated
public class ToastOneUtil {
    private static Toast oneToast;

    /**
     * 单一Toast，前一Toast未消失时后一Toast直接改变前一内容
     * 未知原因，可能时Toast的生命周期造成，连续多次触发会出现后续Toast不出现的问题，待修复，查看源码看到底怎么回事
     * @param context
     * @param msg
     * @param lengthType
     */
    @Deprecated
    public static void show(Context context, String msg, int lengthType) {
        if (null == oneToast) {
            oneToast = Toast.makeText(context.getApplicationContext(), msg, lengthType);
            oneToast.show();
        } else {
            oneToast.setText(msg);
            oneToast.setDuration(lengthType);
            oneToast.show();
        }
    }
}
