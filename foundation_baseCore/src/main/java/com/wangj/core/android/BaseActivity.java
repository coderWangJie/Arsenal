package com.wangj.core.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wangj.core.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类<p>
 *
 * 如需使用EventBus，需要在子类Activity中进行注册/注销，在onStop()、onDestroy()中注销效果是不一样的，按需使用。<p>
 *
 * @author WangJ  2019/4/19
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static String TAG;
    protected Unbinder unbinder;

    /**
     * return the Layout-Resource-ID which will been showed in activity.
     *
     * @return layout-ID
     */
    protected abstract int getContentLayoutRes();

    protected abstract void registerPresenter();

    protected abstract void doBeforeResume();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentLayoutRes() != 0) {
            setContentView(getContentLayoutRes());
        } else {
            setContentView(R.layout.core_no_content);
        }

        // ButterKnife注册
        unbinder = ButterKnife.bind(this);

        // "TAG" will been assigned as SubClass's name.
        TAG = getClass().getSimpleName();

        registerPresenter();

        doBeforeResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // ButterKnife注销
        unbinder.unbind();
    }
}
