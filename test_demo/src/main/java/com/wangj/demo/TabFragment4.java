package com.wangj.demo;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangj.core.android.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TabFragment4 extends BaseFragment {

    @BindView(R2.id.llContainer)
    LinearLayout llContainer;

    @BindView(R2.id.textView)
    TextView textView;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_fragment_tablayout_viewpager;
    }

    @Override
    protected void doAfterViewCreated(View view) {
        llContainer.setBackgroundColor(Color.parseColor("#7EEA76"));
        textView.setText("Fragment-4");
    }
}
