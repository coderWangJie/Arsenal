package com.wangj.demo;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangj.core.android.BaseFragment;

import butterknife.BindView;

public class TabFragment3 extends BaseFragment {

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
        llContainer.setBackgroundColor(Color.parseColor("#B267EA"));
        textView.setText("Fragment-3");
    }
}
