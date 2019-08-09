package com.wangj.demo;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARouterMapping;
import com.wangj.core.android.BaseActivity;

@Route(path = ARouterMapping.DemoMapping.Buttons)
public class ButtonsActivity extends BaseActivity {
    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_buttons;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {

    }
}
