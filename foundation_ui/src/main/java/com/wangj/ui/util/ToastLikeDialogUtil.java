package com.wangj.ui.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangj.ui.R;

public class ToastLikeDialogUtil {

    public interface OnToastLifecycleListener {
        /** 由于{@link ToastLikeDialogUtil}使用Toast实现，所以禁止在本回调中使用Toast */
        void onShow();

        void onDismiss();
    }

    private static int TYPE_SUCCESS = 0;
    private static int TYPE_FAIL = 1;

    public static void toastSuccess(Context context, String msg) {
        toast(context, TYPE_SUCCESS, msg, null);
    }

    public static void toastSuccess(Context context, String msg, OnToastLifecycleListener listener) {
        toast(context, TYPE_SUCCESS, msg, listener);
    }

    public static void toastFail(Context context, String msg) {
        toast(context, TYPE_FAIL, msg, null);
    }

    public static void toastFail(Context context, String msg, OnToastLifecycleListener listener) {
        toast(context, TYPE_FAIL, msg, listener);
    }

    private static void toast(Context context, int type, String msg, final OnToastLifecycleListener dismissListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_view_toast_dialog, null);
        if (dismissListener != null) {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {
                    dismissListener.onShow();
                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    dismissListener.onDismiss();
                }
            });
        }

        ((TextView) view.findViewById(R.id.tvMsg)).setText(msg);

        ImageView imgIcon = view.findViewById(R.id.imgIcon);
        if (type == TYPE_SUCCESS) {
            imgIcon.setImageResource(R.drawable.ui_icon_success);
        } else {
            imgIcon.setImageResource(R.drawable.ui_icon_fail);
        }

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
