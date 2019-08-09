package com.wangj.demo;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARouterMapping;
import com.wangj.core.android.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARouterMapping.DemoMapping.CoordinatorAppBarLayout)
public class CoordinatorAppBarLayoutActivity extends BaseActivity {

    @BindView(R2.id.recyclerViewTop)
    RecyclerView recyclerViewTop;

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_coordinator_appbarlayout;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {
        List<String> tempTop = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            tempTop.add("Item-" + i);
        }

        TestAdapter adapterTop = new TestAdapter(tempTop);
        recyclerViewTop.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTop.setAdapter(adapterTop);

        List<String> temp = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            temp.add("Item-" + i);
        }

        TestAdapter adapter = new TestAdapter(temp);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    }

    class TestAdapter extends RecyclerView.Adapter<TestHolder> {

        private List<String> datas;

        public TestAdapter(List<String> datas) {
            this.datas = datas;
        }

        @NonNull
        @Override
        public TestHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new TestHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_item_test, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull TestHolder viewHolder, int i) {
            viewHolder.tv.setText(datas.get(i));
            viewHolder.tv.setBackgroundColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    class TestHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public TestHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
