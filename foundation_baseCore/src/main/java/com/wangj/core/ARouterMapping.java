package com.wangj.core;

import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.exception.NoRouteFoundException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wangj.core.util.LogUtil;

public class ARouterMapping {

    /**
     * 通过路由地址获取对应 Class
     *
     * @param routerPath
     * @return 对应 Class，返回可能为空
     */
    public static Class getClassByPath(String routerPath) {
        Postcard postcard = ARouter.getInstance().build(routerPath);
        try {
            LogisticsCenter.completion(postcard);
            return postcard.getDestination();
        } catch (NoRouteFoundException ex) {
            LogUtil.e("NoRouteFoundException", ex.getMessage());
        }
        return null;
    }

    /**
     * Demo Module
     */
    public static class DemoMapping {
        public final static String Home = "/demo/index";
        public final static String Buttons = "/demo/Buttons";
        public final static String Dialog = "/demo/Dialog";
        public final static String CoordinatorAppBarLayout = "/demo/CoordinatorAppBarLayout";
        public final static String CardView = "/demo/CardView";
        public final static String Toast = "/demo/Toast";

        public final static String TabLayout = "/demo/TabLayout";
        public final static String TabLayoutViewPager1 = "/demo/TabLayoutViewPager1";
        public final static String TabLayoutViewPager2 = "/demo/TabLayoutViewPager2";
        public final static String TabLayoutViewPager3 = "/demo/TabLayoutViewPager3";
        public final static String TabLayoutRecyclerView1 = "/demo/TabLayoutRecyclerView1";
        public final static String TabLayoutRecyclerView2 = "/demo/TabLayoutRecyclerView2";
        public final static String TabLayoutRecyclerView3 = "/demo/TabLayoutRecyclerView3";

        public final static String Temp = "/demo/x?";

    }

    /**
     *
     */
    public static class LauncherMapping {
        public final static String Home = "/launcher/home";
    }

    /**
     * 登录 Module
     */
    public static class LoginMapping {
        public final static String Login = "/login/login";
    }

    /**
     * Web
     */
    public static class WebMapping {
        public final static String WebView = "/web/webView";
    }
}
