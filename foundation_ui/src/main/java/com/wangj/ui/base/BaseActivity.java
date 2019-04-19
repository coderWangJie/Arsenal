package com.wangj.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wangj.ui.R;

/**
 * Activity基类
 * @author WangJ  2019/4/19
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static String TAG;

    protected abstract int getContentLayoutRes();

    protected abstract void initViews();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentLayoutRes() != 0) {
            setContentView(getContentLayoutRes());
        } else {
            setContentView(R.layout.default_content_not_set);
        }

        TAG = getClass().getSimpleName();

        initViews();
    }
}
