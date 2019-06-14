package com.wangj.demo;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;

@Route(path = ARoutMapping.DemoMapping.Buttons)
public class ButtonsActivity extends BaseActivity {
    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_item_buttons;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void doBeforeResume() {

    }
}
