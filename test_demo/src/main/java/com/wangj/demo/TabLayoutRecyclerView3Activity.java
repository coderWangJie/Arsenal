package com.wangj.demo;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.core.entity.BaseVO;
import com.wangj.core.util.LogUtil;
import com.wangj.ui.CustToastUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import butterknife.BindView;

@Route(path = ARoutMapping.DemoMapping.TabLayoutRecyclerView3)
public class TabLayoutRecyclerView3Activity extends BaseActivity {
    private int SpanCount = 3;

    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    GridLayoutManager gridLayoutManager;

    LinkedHashMap<Section, ArrayList<Item>> map;

    int recyclerViewScrollState = 0;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_tablayout_recyclerview3;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ui_icon_arrow_back_b);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        createData();

        gridLayoutManager = new GridLayoutManager(this, SpanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(null);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LogUtil.d("WangJ", "newState:" + newState);
                recyclerViewScrollState = newState;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.demo_menu_edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.menu_edit_menu) {
            CustToastUtil.toastPositive(this, "菜单标题：".concat(item.getTitle().toString()));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }








    private void createData() {
        map = new LinkedHashMap<>();
        Section section;
        ArrayList<Item> tempList = new ArrayList<>();

        // 模拟菜单组1
        section = new Section("生活");
        tempList.clear();
        for (int i = 0; i < 4; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组2
        section = new Section("工作");
        tempList.clear();
        for (int i = 0; i < 2; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组3
        section = new Section("娱乐频道");
        tempList.clear();
        for (int i = 0; i < 7; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组4
        section = new Section("财富");
        tempList.clear();
        for (int i = 0; i < 3; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组5
        section = new Section("偷懒");
        tempList.clear();
        for (int i = 0; i < 11; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组6
        section = new Section("测试");
        tempList.clear();
        for (int i = 0; i < 8; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组7
        section = new Section("关爱父母");
        tempList.clear();
        for (int i = 0; i < 5; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组8
        section = new Section("游戏");
        tempList.clear();
        for (int i = 0; i < 10; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组9
        section = new Section("LOL");
        tempList.clear();
        for (int i = 0; i < 13; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);
    }




    class Section extends BaseVO {
        private String secName;

        public Section(String secName) {
            this.secName = secName;
        }

        public String getSecName() {
            return secName;
        }

        public void setSecName(String secName) {
            this.secName = secName;
        }
    }

    class Item extends BaseVO {
        private String name;

        public Item(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
