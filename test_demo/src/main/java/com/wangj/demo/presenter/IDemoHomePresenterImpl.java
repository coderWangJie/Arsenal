package com.wangj.demo.presenter;

import com.wangj.core.ARouterMapping;
import com.wangj.demo.model.DemoVO;
import com.wangj.demo.view.IDemoHomeView;

import java.util.ArrayList;

public class IDemoHomePresenterImpl implements IDemoHomePresenter {
    private IDemoHomeView iDemoHomeView;

    public IDemoHomePresenterImpl(IDemoHomeView iDemoHomeView) {
        this.iDemoHomeView = iDemoHomeView;
    }

    @Override
    public void createDemoDatas() {
        ArrayList<DemoVO> list = new ArrayList<>();

        list.add(new DemoVO(ARouterMapping.LoginMapping.Login, "调起Login"));
        list.add(new DemoVO(ARouterMapping.DemoMapping.Buttons, "Button && MaterialButton && AppCompatButton"));
        list.add(new DemoVO(ARouterMapping.DemoMapping.Dialog, "对话框"));
        list.add(new DemoVO(ARouterMapping.DemoMapping.CoordinatorAppBarLayout, "CoordinatorLayout + AppBarLayout模拟支付宝菜单页"));
        list.add(new DemoVO(ARouterMapping.DemoMapping.CardView, "CardView"));
        list.add(new DemoVO(ARouterMapping.DemoMapping.Toast, "各种Toast"));
        list.add(new DemoVO(ARouterMapping.DemoMapping.TabLayout, "TabLayout的使用"));
        list.add(new DemoVO(ARouterMapping.DemoMapping.Temp, "转场动画"));

        iDemoHomeView.updadeDemoList(list);
    }
}
