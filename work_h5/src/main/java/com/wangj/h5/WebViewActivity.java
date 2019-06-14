package com.wangj.h5;

import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;

import butterknife.BindView;

@Route(path = ARoutMapping.WebMapping.WebView)
public class WebViewActivity extends BaseActivity {

    @BindView(R2.id.webView)
    WebView webView;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.h5_webview;
    }

    @Override
    protected void registerPresenter() {

        String url = "http://www.nbcb.com.cn/gryw/yyzc/09/yd_006298.shtml";

        webView.loadUrl(url);
    }

    @Override
    protected void doBeforeResume() {

    }
}
