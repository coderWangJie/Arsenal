package com.wangj.demo;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.ui.CustToastDialogUtil;

import butterknife.OnClick;

@Route(path = ARoutMapping.DemoMapping.Toast)
public class ToastActivity extends BaseActivity {
    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_toast;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void doBeforeResume() {

    }

    @OnClick(R2.id.button1)
    void buttonClick(View view) {
        CustToastDialogUtil.toastSuccess(this, 0, "操作成功");
    }
}
