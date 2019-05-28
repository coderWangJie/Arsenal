package com.wangj.launcher;

import android.os.SystemClock;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangj.core.android.BaseActivity;

import java.sql.Time;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private long lastBankPressedTime = 0;

    @BindView(R2.id.textView)
    TextView tv;
    @BindView(R2.id.imgDemo)
    ImageView imgDemo;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.launcher_activity_main;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastBankPressedTime <= 1000 * 2) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            lastBankPressedTime = currentTime;
        }

    }
}
