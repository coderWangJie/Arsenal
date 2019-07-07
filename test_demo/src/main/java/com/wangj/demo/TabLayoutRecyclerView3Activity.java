package com.wangj.demo;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.core.entity.BaseVO;
import com.wangj.core.util.LogUtil;
import com.wangj.demo.adapter.TablayoutRecyclerViewAdapter;
import com.wangj.ui.CustToastUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;

@Route(path = ARoutMapping.DemoMapping.TabLayoutRecyclerView3)
public class TabLayoutRecyclerView3Activity extends BaseActivity {
    /** 每行展示菜单数量 */
    private int SPAN_COUNT = 3;

    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private GridLayoutManager gridLayoutManager;
    private TablayoutRecyclerViewAdapter adapter;

    private LinkedHashMap<Section, ArrayList<Item>> map;
    private ArrayList<BaseVO> dataList;

    /**
     * RecyclerView的滑动状态标记<br/>
     * {@link RecyclerView#SCROLL_STATE_IDLE}：The RecyclerView is not currently scrolling;<br/>
     * {@link RecyclerView#SCROLL_STATE_DRAGGING}：The RecyclerView is currently being dragged by outside input such as user touch input;<br/>
     * {@link RecyclerView#SCROLL_STATE_SETTLING}：The RecyclerView is currently animating to a final position while not under outside control.<br/>
     */
    private int recyclerViewScrollState = 0;

    /**  */
    private int lastTabSelectPos = -1;
    /**  */
    private int tabSelectPos;

    /**  */
    private int lastTimeRecyclerPos = -1;
    /**  */
    private int currentFirstVisiblePos;

    /** 最后一个菜单组中菜单项的数量 */
    private int lastSectionChildNum = 0;

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

        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new TablayoutRecyclerViewAdapter(dataList, gridLayoutManager, true);
        adapter.setLastSectionChildCount(lastSectionChildNum);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LogUtil.d(TAG, "recyclerView newState:" + newState);
                recyclerViewScrollState = newState;
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentFirstVisiblePos = gridLayoutManager.findFirstVisibleItemPosition();
                if (lastTimeRecyclerPos != currentFirstVisiblePos) {
                    lastTimeRecyclerPos = currentFirstVisiblePos;
                    tabSelectPos = calculateTabPosByRecyclerPos(lastTimeRecyclerPos);
                    if (lastTabSelectPos != tabSelectPos) {
                        tabLayout.getTabAt(lastTabSelectPos = tabSelectPos).select();
                    }
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabSelectedChange(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                onTabSelectedChange(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelectedChange(tab, true);
            }
        });

        // 记录RecyclerView的容器高度
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                adapter.setContainerHeight(recyclerView.getHeight());
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void onTabSelectedChange(TabLayout.Tab tab, boolean isSelected) {
        if (isSelected) {
            ((TextView)tab.getCustomView().findViewById(R.id.tv)).getPaint().setFakeBoldText(true);
            // 用RecyclerView状态做过滤，防止RecyclerView由于惯性滑动触发Tab选中反过来再滑动RecyclerView导致的使RecyclerView一下滑到顶的问题
            if (recyclerViewScrollState == RecyclerView.SCROLL_STATE_IDLE) {
                gridLayoutManager.scrollToPositionWithOffset(calculateRecyclerPosByTabPos(tab.getPosition()), 0);
            }
        } else {
            ((TextView)tab.getCustomView().findViewById(R.id.tv)).getPaint().setFakeBoldText(false);
        }
    }

    /**
     * 根据RecyclerView的索引计算TabLayout对应应该选中的Tab的索引
     * @param recyclerPos RecyclerView首个可见元素的索引
     * @return 对应Tab的索引
     */
    private int calculateTabPosByRecyclerPos(int recyclerPos) {
        int index = 0;
        int count = 0;
        for (LinkedHashMap.Entry<Section, ArrayList<Item>> item : map.entrySet()) {
            count = count + 1 + item.getValue().size();
            if (count <= recyclerPos) {
                index++;
            } else {
                return index;
            }
        }
        return index;
    }

    /**
     * 根据选中的TabLayout中Tab的索引计算RecyclerView展示在顶部的item的索引
     * @param tabPos tab的索引
     * @return 第一个可见item的索引
     */
    private int calculateRecyclerPosByTabPos(int tabPos) {
        int index = 0;
        int count = 0;
        for (LinkedHashMap.Entry<Section, ArrayList<Item>> item : map.entrySet()) {
            if (index++ < tabPos) {
                count = count + 1 + item.getValue().size();
            } else {
                return count;
            }
        }
        return count;
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


    /**
     * 生成模拟数据
     */
    private void createData() {
        map = new LinkedHashMap<>();
        Section section;
        ArrayList<Item> tempList;

        // 模拟菜单组1
        section = new Section("生活");
        tempList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组2
        section = new Section("工作");
        tempList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组3
        section = new Section("娱乐频道");
        tempList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组4
        section = new Section("财富");
        tempList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组5
        section = new Section("偷懒");
        tempList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组6
        section = new Section("测试");
        tempList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组7
        section = new Section("关爱父母");
        tempList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组8
        section = new Section("游戏");
        tempList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        // 模拟菜单组9
        section = new Section("LOL");
        tempList = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            tempList.add(new Item(section.getSecName().concat(String.valueOf(i))));
        }
        map.put(section, tempList);

        dataList = new ArrayList<>();
        for (Map.Entry<Section, ArrayList<Item>> item : map.entrySet()) {
            Section key = item.getKey();

            // 对每个菜单栏目标题添加自定义tab
            TabLayout.Tab tabItem = tabLayout.newTab();
            tabItem.setCustomView(R.layout.demo_item_tablayout_tab);
            ((TextView)tabItem.getCustomView().findViewById(R.id.tv)).setText(key.getSecName());
            tabLayout.addTab(tabItem);

            dataList.add(key);

            dataList.addAll(item.getValue());
            // 每次循环记录本组菜单数量，最后一次更新值即为最后一组菜单数量
            lastSectionChildNum = item.getValue().size();
        }
    }

    public class Section extends BaseVO {
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

    public class Item extends BaseVO {
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
