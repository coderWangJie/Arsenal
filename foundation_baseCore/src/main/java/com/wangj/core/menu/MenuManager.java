package com.wangj.core.menu;

import java.util.List;

public class MenuManager {

    public static List<MenuGroupVO> queryMenuGroups() {
        return MenuDAO.getInstance().selectAllMenuGroup();
    }

    public static List<MenuItemVO> queryMenus() {
        return MenuDAO.getInstance().selectAllMenu();
    }

    public static boolean saveMenuGroup(MenuGroupVO menuGroupVo) {
        return MenuDAO.getInstance().saveMenuGroupToDB(menuGroupVo);
    }

    public static boolean saveMenu(MenuItemVO menuItemVO) {
        return MenuDAO.getInstance().saveMenuToDB(menuItemVO);
    }

    public static boolean clearAllMenu() {
        // 删除菜单组
        MenuDAO.getInstance().deleltAllMenuGroup();
        // 删除菜单
        MenuDAO.getInstance().deleltAllMenu();
        return true;
    }
}
