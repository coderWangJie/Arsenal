package com.wangj.demo.android;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.demo.R;

@Route(path = ARoutMapping.DemoMapping.Index)
public class DemoActivity extends BaseActivity {

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_demo;
    }

    @Override
    protected void registerPresenter() {

    }
}
