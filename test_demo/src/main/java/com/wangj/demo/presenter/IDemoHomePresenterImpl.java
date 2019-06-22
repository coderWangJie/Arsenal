package com.wangj.demo.presenter;

import com.wangj.core.ARoutMapping;
import com.wangj.demo.model.DemoVo;
import com.wangj.demo.view.IDemoHomeView;

import java.util.ArrayList;

public class IDemoHomePresenterImpl implements IDemoHomePresenter {
    private IDemoHomeView iDemoHomeView;

    public IDemoHomePresenterImpl(IDemoHomeView iDemoHomeView) {
        this.iDemoHomeView = iDemoHomeView;
    }

    @Override
    public void createDemoDatas() {
        ArrayList<DemoVo> list = new ArrayList<>();

        list.add(new DemoVo(ARoutMapping.LoginMapping.Login, "调起Login"));
        list.add(new DemoVo(ARoutMapping.DemoMapping.Buttons, "Button && MaterialButton && AppCompatButton"));
        list.add(new DemoVo(ARoutMapping.DemoMapping.CoordinatorAppBarLayout, "CoordinatorLayout + AppBarLayout模拟支付宝菜单页"));
        list.add(new DemoVo(ARoutMapping.DemoMapping.CardView, "CardView"));
        list.add(new DemoVo(ARoutMapping.DemoMapping.Toast, "各种Toast"));
        list.add(new DemoVo(ARoutMapping.DemoMapping.TabLayout, "TabLayout的使用"));
        list.add(new DemoVo(ARoutMapping.DemoMapping.Temp, "转场动画"));

        iDemoHomeView.updadeDemoList(list);
    }
}
