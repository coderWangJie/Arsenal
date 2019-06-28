package com.wangj.demo;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.demo.adapter.DemoListAdapter;
import com.wangj.demo.model.DemoVO;
import com.wangj.demo.presenter.IDemoHomePresenter;
import com.wangj.demo.presenter.IDemoHomePresenterImpl;
import com.wangj.demo.view.IDemoHomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ARoutMapping.DemoMapping.Home)
public class DemoActivity extends BaseActivity implements IDemoHomeView {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    IDemoHomePresenter iDemoHomePresenter;

    private ArrayList<DemoVO> list;
    private DemoListAdapter adapter;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_home;
    }

    @Override
    protected void registerPresenter() {
        iDemoHomePresenter = new IDemoHomePresenterImpl(this);
    }

    @Override
    protected void initOnCreate() {
        list = new ArrayList<>();
        adapter = new DemoListAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        iDemoHomePresenter.createDemoDatas();
    }

    @Override
    public void updadeDemoList(List<DemoVO> data) {
        list.clear();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
