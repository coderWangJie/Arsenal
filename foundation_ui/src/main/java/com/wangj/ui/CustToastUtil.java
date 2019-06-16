package com.wangj.ui;

import android.content.Context;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustToastUtil {

    private static int TYPE_POSITIVE = 0;
    private static int TYPE_NEUTRAL = 1;
    private static int TYPE_NEGATIVE = 2;

    public static void toastPositive(Context context, String msg) {
        toast(context, TYPE_POSITIVE, msg);
    }

    public static void toastNeutral(Context context, String msg) {
        toast(context, TYPE_NEUTRAL, msg);
    }

    public static void toastNegative(Context context, String msg) {
        toast(context, TYPE_NEGATIVE, msg);
    }

    private static void toast(Context context, int type, String msg) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_view_toast_cust, null);
        ((TextView) view.findViewById(R.id.tvMessage)).setText(msg);


        ImageView imgIcon = view.findViewById(R.id.imgIcon);
        if (type == TYPE_POSITIVE) {
            imgIcon.setImageResource(R.drawable.ui_frame_face_positive);
        } else if (type == TYPE_NEUTRAL) {
            imgIcon.setImageResource(R.drawable.ui_frame_face_neutral);
        } else {
            imgIcon.setImageResource(R.drawable.ui_frame_face_negative);
        }

        // 这里帧动画不用启动也可以播放
        AnimationDrawable animation = (AnimationDrawable) imgIcon.getDrawable();
        animation.start();

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
