package com.wangj.demo;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.core.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARoutMapping.DemoMapping.TabLayoutRecyclerView2)
public class TabLayoutRecyclerView2Activity extends BaseActivity {

    @BindView(R2.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> dataList;

    LinearLayoutManager linearLayoutManager;

    int lastPos;
    int recyclerViewScrollState = 0;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_tablayout_recyclerview2;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {
        dataList = new ArrayList<>();
        dataList.add("这个是第一个");
        dataList.add("2");
        dataList.add("3");
        dataList.add("I'am Fourth");
        dataList.add("第5个");
        dataList.add("第六");
        dataList.add("第7");
        dataList.add("第88888888888888");
        dataList.add("第99");

        MyAdapter adapter = new MyAdapter();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < dataList.size(); i++) {
            tabLayout.addTab(tabLayout.newTab(), i == 0);

            View view = getLayoutInflater().inflate(R.layout.demo_item_tab_title, null);
            ((TextView) view.findViewById(R.id.tvTitle)).setText(dataList.get(i));
            tabLayout.getTabAt(i).setCustomView(view);
        }

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

                if (lastPos != linearLayoutManager.findFirstVisibleItemPosition()) {
                    lastPos = linearLayoutManager.findFirstVisibleItemPosition();
//                    tabLayout.setScrollPosition(lastPos, 0, true);
                    tabLayout.getTabAt(lastPos).select();
                }
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();

                TextView tvTitle = view.findViewById(R.id.tvTitle);
                tvTitle.getPaint().setFakeBoldText(true);
                // 设置粗体后强制刷新一下TextView，否则有可能在切换中设置失效，导致粗体/正常体切换失效---验证发现整个刷view无效---
                tvTitle.postInvalidate();

                ImageView imgIcon = view.findViewById(R.id.imgIcon);
                imgIcon.setImageResource(R.drawable.ui_face_positive);

                if (recyclerViewScrollState == 0) {
                    linearLayoutManager.scrollToPositionWithOffset(tabLayout.getSelectedTabPosition(), 0);
                }
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

                if (recyclerViewScrollState == 0) {
                    linearLayoutManager.scrollToPositionWithOffset(tabLayout.getSelectedTabPosition(), 0);
                }
            }
        });

    }


    class MyAdapter extends RecyclerView.Adapter<MyHolder> {

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_item_test, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
            myHolder.textView.setText(dataList.get(i));
            myHolder.textView.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
            textView.setHeight(800);
        }
    }
}
