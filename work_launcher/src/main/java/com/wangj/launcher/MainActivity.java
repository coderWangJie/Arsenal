package com.wangj.launcher;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wangj.core.ARouterMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.ui.ShareButtonView;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARouterMapping.LauncherMapping.Home)
public class MainActivity extends BaseActivity {

    private long lastBankPressedTime = 0;

    @BindView(R2.id.textView)
    TextView tv;

    @BindView(R2.id.shareButton)
    ShareButtonView shareButton;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.launcher_activity_main;
    }

    @Override
    protected void registerPresenter() {

    }

    int width;
    int height;

    @Override
    protected void initOnCreate() {
//        final LinearLayout ll = findViewById(R.id.llLayout);
//
//        ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                // 获取宽高
//                width = ll.getWidth();
//                height = ll.getHeight();
//                // 触发后即移除该监听，否则会多次触发，影响性能
//                ll.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//
//                // 渐变
//                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor("#FFFFFF"), Color.parseColor("#52ED5A"), Color.parseColor("#EF5353")});
//                gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
//                // (0.5f, 0.5f)为默认渐变中心
//                gradientDrawable.setGradientCenter(0.5f, 0.5f);
//                gradientDrawable.setGradientRadius(height / 2);
//
//                ll.setBackground(gradientDrawable);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R2.id.imgDemo)
    void onViewClick(View view) {
        if (view.getId() == R.id.imgDemo) {
            ARouter.getInstance().build(ARouterMapping.DemoMapping.Home).navigation();

//            MenuItemVO menuItemVO = new MenuItemVO();
//            menuItemVO.setID("1001");
//            menuItemVO.setName("测试菜单");
//            menuItemVO.setIconUrl("xxxx/xxxx");
//            menuItemVO.setActionType("N");
//            menuItemVO.setGroupID("101");
//            menuItemVO.setSort(9);
//            MenuManager.saveMenu(menuItemVO);

//            MenuGroupVO groupVo = new MenuGroupVO();
//            groupVo.setGroupID("10");
//            groupVo.setGroupName("一号组");
//            groupVo.setSort(1);
//            MenuManager.saveMenuGroup(groupVo);

//            MenuDAO.getInstance().deleltAllMenuGroup();

//            MenuManager.queryMenuGroups();

//            MenuDAO.getInstance().selectAllMenu();

//            MenuDAO.getInstance().selectMenuByGroupID("101");

//            ARouter.getInstance().build(ARouterMapping.WebMapping.WebView)
//                    .withString(IntentConstant.WEB_URL, "")
//                    .withString(IntentConstant.WEB_TITLE, "")
//                    .navigation();
        }
    }

    int temp;

    @OnClick(R2.id.shareButton)
    void actionShareButtonView() {
        if (temp++ % 2 == 0) {
            shareButton.startAnimation();
        } else {
            shareButton.reset();
        }
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
