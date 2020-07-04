package com.wangj.ui.util;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.wangj.core.util.LogUtil;

import java.lang.reflect.Field;

/**
 * 可点击的Toast
 */
public class ToastClickableUtil {
    private final String TAG = getClass().getSimpleName();

    private Toast mToast;

    public void show(Context mContext, View view) {
        if (mToast == null) {
            mToast = new Toast(mContext.getApplicationContext());
        }
        mToast.setView(view);
        // 自定义Toast位置，位于屏幕右侧，距顶部1/4屏幕高度处
        WindowManager windowManager = (WindowManager) mContext.getApplicationContext().getSystemService(Application.WINDOW_SERVICE);
        Point size = new Point();
        windowManager.getDefaultDisplay().getSize(size);
        mToast.setGravity(Gravity.END | Gravity.TOP, 0, size.y / 4);
        mToast.setDuration(Toast.LENGTH_LONG);

        /*
         * Toast默认不接收点击事件，通过反射更改属性使其可点击
         */
        try {
            Object mTN = getField(mToast, "mTN");
            if (mTN != null) {
                Object mParams = getField(mTN, "mParams");
                if (mParams instanceof WindowManager.LayoutParams) {
                    WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams;
                    // 设置Toast可点击
                    params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

                    //设置viewgroup宽高
                    params.width = WindowManager.LayoutParams.WRAP_CONTENT; //设置宽度
                    params.height = WindowManager.LayoutParams.WRAP_CONTENT; //设置高度
                }
            }
        } catch (NoSuchFieldException e) {
            LogUtil.e(TAG, "反射设置Toast异常 NoSuchFieldException:" + e.getMessage());
        } catch (IllegalAccessException e) {
            LogUtil.e(TAG, "反射设置Toast异常 IllegalAccessException:" + e.getMessage());
        }

        mToast.show();
    }


    /**
     * 反射获取对象
     */
    private Object getField(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
