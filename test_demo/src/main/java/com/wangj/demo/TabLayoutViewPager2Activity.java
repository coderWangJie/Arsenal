package com.wangj.demo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.demo.model.TabViewPagerVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARoutMapping.DemoMapping.TabLayoutViewPager2)
public class TabLayoutViewPager2Activity extends BaseActivity {

    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    FragmentManager manager;
    private FragmentTransaction transtion;

    private List<TabViewPagerVO> dataList;

    private MyFragmentPageAdapter adapter;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_tablayout_viewpager2;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {
        dataList = new ArrayList<>();

        TabViewPagerVO item1 = new TabViewPagerVO("这个是第一个", new TabFragment1());
        dataList.add(item1);
        TabViewPagerVO item2 = new TabViewPagerVO("2", new TabFragment2());
        dataList.add(item2);
        TabViewPagerVO item3 = new TabViewPagerVO("3", new TabFragment3());
        dataList.add(item3);
        TabViewPagerVO item4 = new TabViewPagerVO("I'am Fourth", new TabFragment4());
        dataList.add(item4);
        TabViewPagerVO item5 = new TabViewPagerVO("第5个", new TabFragment1());
        dataList.add(item5);
        TabViewPagerVO item6 = new TabViewPagerVO("第六", new TabFragment2());
        dataList.add(item6);

        //使用manager和transtion提交事务后为viewpager设置一个适配器
        manager = getSupportFragmentManager();
        transtion = manager.beginTransaction();
        transtion.commit();

        adapter = new MyFragmentPageAdapter(manager, dataList);
        viewPager.setAdapter(adapter);

        //这两个方法是将Tablayout和Viewpager联合起来
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setTabsFromPagerAdapter(adapter);

        for (int i = 0; i < dataList.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.demo_item_tab_title, null);
            ((TextView) view.findViewById(R.id.tvTitle)).setText(dataList.get(i).getTitle());
            tabLayout.getTabAt(i).setCustomView(view);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // 从调试的结果来看，此方法回调后都会回调一次onTabReselected()方法，所以此处乐意不做处理，只在onTabReselected()中处理
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();

                TextView tvTitle = view.findViewById(R.id.tvTitle);
                tvTitle.getPaint().setFakeBoldText(false);
                // 设置粗体后强制刷新一下TextView，否则有可能在切换中设置失效，导致粗体/正常体切换失效---验证发现整个刷view无效---
                tvTitle.postInvalidate();

                ImageView imgIcon = view.findViewById(R.id.imgIcon);
                imgIcon.setImageResource(R.drawable.ui_frame_face_neutral);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();

                TextView tvTitle = view.findViewById(R.id.tvTitle);
                tvTitle.getPaint().setFakeBoldText(true);
                // 设置粗体后强制刷新一下TextView，否则有可能在切换中设置失效，导致粗体/正常体切换失效---验证发现整个刷view无效---
                tvTitle.postInvalidate();

                ImageView imgIcon = view.findViewById(R.id.imgIcon);
                imgIcon.setImageResource(R.drawable.ui_face_positive);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                tabLayout.getTabAt(i).select();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        // 需要手动设置选中，否则不生效
        tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).select();
    }


    private class MyFragmentPageAdapter extends FragmentPagerAdapter {

        private List<TabViewPagerVO> list;

        public MyFragmentPageAdapter(FragmentManager fm, List<TabViewPagerVO> list) {
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
