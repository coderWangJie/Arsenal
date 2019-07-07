package com.wangj.core.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangj.core.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类<p>
 * 如需使用EventBus，需要在子类Activity中进行注册/注销，在onStop()、onDestroy()中注销效果是不一样的，按需使用。<p>
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;
    private BaseActivity activity;

    protected abstract int getContentLayoutRes();

    protected abstract void doAfterViewCreated(View view);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity actvty) {
        super.onAttach(activity);
        if (actvty instanceof BaseActivity) {
            activity = (BaseActivity) actvty;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        if (getContentLayoutRes() != 0) {
            view = inflater.inflate(getContentLayoutRes(), container, false);
        } else {
            view = inflater.inflate(R.layout.core_no_content, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doAfterViewCreated(view);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
