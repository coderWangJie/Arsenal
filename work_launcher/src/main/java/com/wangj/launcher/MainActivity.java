package com.wangj.launcher;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wangj.core.ARoutMapping;
import com.wangj.core.android.BaseActivity;
import com.wangj.core.menu.MenuDAO;
import com.wangj.core.menu.MenuGroupVO;
import com.wangj.core.menu.MenuItemVO;
import com.wangj.core.menu.MenuManager;
import com.wangj.core.sqlite.DBConstant;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ARoutMapping.LauncherMapping.Home)
public class MainActivity extends BaseActivity {

    private long lastBankPressedTime = 0;

    @BindView(R2.id.textView)
    TextView tv;

    @Override
    protected int getContentLayoutRes() {
        return R.layout.launcher_activity_main;
    }

    @Override
    protected void registerPresenter() {

    }

    @Override
    protected void initOnCreate() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R2.id.imgDemo)
    void onViewClick(View view) {
        if (view.getId() == R.id.imgDemo) {
            ARouter.getInstance().build(ARoutMapping.DemoMapping.Home).navigation();

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


//            ARouter.getInstance().build(ARoutMapping.WebMapping.WebView)
//                    .withString(IntentConstant.WEB_URL, "")
//                    .withString(IntentConstant.WEB_TITLE, "")
//                    .navigation();
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
