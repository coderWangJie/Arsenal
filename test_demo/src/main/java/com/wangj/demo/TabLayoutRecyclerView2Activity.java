package com.wangj.demo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.demo.model.TabViewPagerVo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARoutMapping.DemoMapping.TabLayoutRecyclerView2)
public class TabLayoutRecyclerView2Activity extends BaseActivity {

    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    FragmentManager manager;
    private FragmentTransaction transtion;

    private List<TabViewPagerVo> dataList;

    private MyFragmentPageAdapter adapter;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_tablayout_recyclerview2;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void doBeforeResume() {
        dataList = new ArrayList<>();

        TabViewPagerVo item1 = new TabViewPagerVo("这个是第一个", new TabFragment1());
        dataList.add(item1);

        TabViewPagerVo item2 = new TabViewPagerVo("2", new TabFragment2());
        dataList.add(item2);

        TabViewPagerVo item3 = new TabViewPagerVo("3", new TabFragment3());
        dataList.add(item3);

        TabViewPagerVo item4 = new TabViewPagerVo("I'am Fourth", new TabFragment4());
        dataList.add(item4);

        TabViewPagerVo item5 = new TabViewPagerVo("第5个", new TabFragment1());
        dataList.add(item5);

        TabViewPagerVo item6 = new TabViewPagerVo("第六", new TabFragment2());
        dataList.add(item6);

        //使用manager和transtion提交事务后为viewpager设置一个适配器
        manager = getSupportFragmentManager();
        adapter = new MyFragmentPageAdapter(manager, dataList);
        transtion = manager.beginTransaction();
        transtion.commit();
        viewPager.setAdapter(adapter);

        // tablayout.addTab可以将标题添加进Tab里面，true表示默认选中
        tabLayout.addTab(tabLayout.newTab().setText(dataList.get(0).getTitle()), true);
        tabLayout.addTab(tabLayout.newTab().setText(dataList.get(1).getTitle()), false);
        tabLayout.addTab(tabLayout.newTab().setText(dataList.get(2).getTitle()), false);
        tabLayout.addTab(tabLayout.newTab().setText(dataList.get(3).getTitle()), false);
        tabLayout.addTab(tabLayout.newTab().setText(dataList.get(4).getTitle()), false);
        tabLayout.addTab(tabLayout.newTab().setText(dataList.get(5).getTitle()), false);

        //这两个方法是将Tablayout和Viewpager联合起来
        tabLayout.setupWithViewPager(viewPager);
        //tablayout.setTabsFromPagerAdapter(adapter);

    }


    private class MyFragmentPageAdapter extends FragmentPagerAdapter {

        private List<TabViewPagerVo> list;

        public MyFragmentPageAdapter(FragmentManager fm, List<TabViewPagerVo> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position).getFragment();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getTitle();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
