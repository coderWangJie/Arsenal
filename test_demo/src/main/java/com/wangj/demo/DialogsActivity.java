package com.wangj.demo;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARouterMapping;
import com.wangj.core.android.BaseActivity;

import butterknife.OnClick;

@Route(path = ARouterMapping.DemoMapping.Dialog)
public class DialogsActivity extends BaseActivity {
    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_dialogs;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {

    }

    @OnClick(R2.id.btnFullScreenDialog)
    void showFullScreenDialog() {
        // TODO 刘海屏有适配问题
        final Dialog dialog = new Dialog(this, R.style.AppTheme_FullScreen);

        View view = LayoutInflater.from(this).inflate(R.layout.demo_dialog_full_screen, null);
        view.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.setCancelable(false);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.alpha = 0.96f;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dialog.show();
    }
}
