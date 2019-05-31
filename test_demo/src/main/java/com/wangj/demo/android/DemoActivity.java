package com.wangj.demo.android;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.demo.R;
import com.wangj.demo.R2;

import butterknife.OnClick;

@Route(path = ARoutMapping.DemoMapping.Index)
public class DemoActivity extends BaseActivity {

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_demo;
    }

    @Override
    protected void registerPresenter() {

    }

    @OnClick(R2.id.btnLogin)
    void callClick() {
        ARouter.getInstance().build(ARoutMapping.LoginMapping.Login).navigation();
    }
}
