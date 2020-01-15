package com.wangj.demo;

import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARouterMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.core.util.LogUtil;
import com.wangj.ui.ToastLikeDialogUtil;
import com.wangj.ui.ToastEmotionalUtil;
import com.wangj.ui.ToastOneUtil;

import butterknife.OnClick;

@Route(path = ARouterMapping.DemoMapping.Toast)
public class ToastActivity extends BaseActivity {
    @Override
    protected int getContentLayoutRes() {
        return R.layout.demo_activity_toast;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {

    }

    private int random;

    @OnClick(R2.id.buttonToastOne)
    void buttonOneToast() {
        random = (int) (100 * Math.random());
        ToastOneUtil.show(this, "当前值：" + random, Toast.LENGTH_SHORT);
    }

    @OnClick(R2.id.buttonSuccess1)
    void buttonSuccess1() {
        ToastLikeDialogUtil.toastSuccess(this, "操作成功");
    }

    @OnClick(R2.id.buttonSuccess2)
    void buttonSuccess2() {
        ToastLikeDialogUtil.toastSuccess(this, "操作成功", new ToastLikeDialogUtil.OnToastLifecycleListener() {
            @Override
            public void onShow() {
                LogUtil.d(TAG, "交易成功提示展示");
            }

            @Override
            public void onDismiss() {
                Toast.makeText(ToastActivity.this, "交易成功提示消失", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R2.id.buttonFail1)
    void buttonFail1() {
        ToastLikeDialogUtil.toastFail(this, "交易失败");
    }

    @OnClick(R2.id.buttonFail2)
    void buttonFail2() {
        ToastLikeDialogUtil.toastFail(this, "交易失败", new ToastLikeDialogUtil.OnToastLifecycleListener() {
            @Override
            public void onShow() {
                LogUtil.d(TAG, "交易失败提示展示");
            }

            @Override
            public void onDismiss() {
                Toast.makeText(ToastActivity.this, "交易失败提示消失", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R2.id.buttonPositive)
    void buttonPositive() {
        ToastEmotionalUtil.toastPositive(this, "乐观的Toast");
    }

    @OnClick(R2.id.buttonNeutral)
    void buttonNeutral() {
        ToastEmotionalUtil.toastNeutral(this, "中性的Toast");
    }

    @OnClick(R2.id.buttonNegative)
    void buttonNegative() {
        ToastEmotionalUtil.toastNegative(this, "悲观的Toast");
    }
}
