package com.wangj.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustToastDialogUtil {
    public static void toastSuccess(Context context, int type, String msg) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_cust_toast_view, null);

        ((TextView) view.findViewById(R.id.tvMsg)).setText(msg);
        ImageView imgIcon = view.findViewById(R.id.imgIcon);
        imgIcon.setImageResource(R.drawable.ui_icon_success);

        // 这里帧动画不用启动也可以播放
//        AnimationDrawable animation = (AnimationDrawable) imgIcon.getDrawable();
//        animation.start();

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
