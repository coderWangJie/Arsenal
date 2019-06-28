package com.wangj.demo;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;

import butterknife.OnClick;

@Route(path = ARoutMapping.DemoMapping.TabLayout)
public class TabLayoutActivity extends BaseActivity {
    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_tablayout;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {

    }

    @OnClick(R2.id.tabLayoutViewPager1)
    void tabLayoutViewPager1() {
        ARouter.getInstance().build(ARoutMapping.DemoMapping.TabLayoutViewPager1).navigation();
    }

    @OnClick(R2.id.tabLayoutViewPager2)
    void tabLayoutViewPager2() {
        ARouter.getInstance().build(ARoutMapping.DemoMapping.TabLayoutViewPager2).navigation();
    }

    @OnClick(R2.id.tabLayoutViewPager3)
    void tabLayoutViewPager3() {
        ARouter.getInstance().build(ARoutMapping.DemoMapping.TabLayoutViewPager3).navigation();
    }

    @OnClick(R2.id.tabLayoutRecyclerView1)
    void tabLayoutRecyclerView1() {
        ARouter.getInstance().build(ARoutMapping.DemoMapping.TabLayoutRecyclerView1).navigation();
    }

    @OnClick(R2.id.tabLayoutRecyclerView2)
    void tabLayoutRecyclerView2() {
        ARouter.getInstance().build(ARoutMapping.DemoMapping.TabLayoutRecyclerView2).navigation();
    }

    @OnClick(R2.id.tabLayoutRecyclerView3)
    void tabLayoutRecyclerView3() {
        ARouter.getInstance().build(ARoutMapping.DemoMapping.TabLayoutRecyclerView3).navigation();
    }
}
