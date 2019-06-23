package com.wangj.core.menu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wangj.core.android.BaseApplication;
import com.wangj.core.sqlite.DBConstant;
import com.wangj.core.sqlite.DBOpenHelper;

import com.wangj.core.sqlite.DBConstant.Menu;
import com.wangj.core.sqlite.DBConstant.MenuGroup;
import com.wangj.core.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    private static final String TAG = MenuDAO.class.getSimpleName();

    private static MenuDAO instance;

    private SQLiteDatabase db;
    private ContentValues contentValues;

    private MenuDAO() {
        db = new DBOpenHelper(BaseApplication.getAppContext(), DBConstant.DB_NAME, null, 2).getWritableDatabase();
        contentValues = new ContentValues();
    }

    public static MenuDAO getInstance() {
        // TODO 单例
        if (instance == null) {
            instance = new MenuDAO();
        }
        return instance;
    }

    public boolean saveMenuGroupToDB(MenuGroupVO groupVo) {
        contentValues.clear();
        contentValues.put(MenuGroup.GROUP_ID, groupVo.getGroupID());
        contentValues.put(MenuGroup.GROUP_NAME, groupVo.getGroupName());
        contentValues.put(MenuGroup.SORT, groupVo.getSort());

        long resultCode = db.insert(MenuGroup.TABLE_MAME, null, contentValues);
        LogUtil.d(TAG, "菜单组入表resultCode:" + resultCode);

        return resultCode != -1;
    }

    public boolean saveMenuGroupToDB(List<MenuGroupVO> groupList) {
        // TODO 要不要用事务，防止某些菜单组入库失败？
        for (MenuGroupVO item : groupList) {
            saveMenuGroupToDB(item);
        }
        return true;
    }

    public boolean saveMenuToDB(MenuItemVO menuItemVo) {
        contentValues.clear();
        contentValues.put(Menu.MENU_ID, menuItemVo.getID());
        contentValues.put(Menu.MENU_NAME, menuItemVo.getName());
        contentValues.put(Menu.MENU_ICON, menuItemVo.getIconUrl());
        contentValues.put(Menu.MENU_ACTION_TYPE, menuItemVo.getActionType());
        contentValues.put(Menu.MENU_GTOUP_ID, menuItemVo.getGroupID());
        contentValues.put(Menu.SORT, menuItemVo.getSort());

        long resultCode = db.insert(Menu.TABLE_MAME, null, contentValues);
        LogUtil.d(TAG, "菜单项入表resultCode:" + resultCode);

        return resultCode != -1;
    }

    public boolean saveMenuToDB(List<MenuItemVO> menuList) {
        // TODO 要不要用事务，防止某些菜单入库失败？
        for (MenuItemVO item : menuList) {
            saveMenuToDB(item);
        }
        return true;
    }

    public List<MenuGroupVO> selectAllMenuGroup() {
        List<MenuGroupVO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(MenuGroup.TABLE_MAME);
        Cursor cursor = db.rawQuery(sql.toString(), null);
        while (cursor.moveToNext()) {
            list.add(createMenuGroup(cursor));
        }
        cursor.close();
        return list;
    }

    public List<MenuItemVO> selectAllMenu() {
        List<MenuItemVO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(Menu.TABLE_MAME);
        Cursor cursor = db.rawQuery(sql.toString(), null);
        while (cursor.moveToNext()) {
            list.add(createMenu(cursor));
        }
        cursor.close();
        return list;
    }

    public List<MenuItemVO> selectMenuByGroupID(String groupID) {
        List<MenuItemVO> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(Menu.TABLE_MAME)
                .append(" WHERE ").append(Menu.MENU_GTOUP_ID).append(" = ?");
        Cursor cursor = db.rawQuery(sql.toString(), new String[]{groupID});
        while (cursor.moveToNext()) {
            list.add(createMenu(cursor));
        }
        cursor.close();
        return list;
    }

    public MenuItemVO selectMenuByMenuID(String menuID) {
        MenuItemVO menuItemVo = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(Menu.TABLE_MAME)
                .append(" WHERE ").append(Menu.MENU_ID).append(" = ?");
        Cursor cursor = db.rawQuery(sql.toString(), new String[]{menuID});
        while (cursor.moveToNext()) {
            menuItemVo = createMenu(cursor);
        }
        cursor.close();
        return menuItemVo;
    }

    public void deleltAllMenuGroup() {
        int resultCode = db.delete(MenuGroup.TABLE_MAME, null, null);
        LogUtil.d(TAG, "清除菜单组表数据resultCode：" + resultCode);
    }

    public void deleltAllMenu() {
        int resultCode = db.delete(Menu.TABLE_MAME, null, null);
        LogUtil.d(TAG, "清除菜单表数据resultCode：" + resultCode);
    }


    private MenuGroupVO createMenuGroup(Cursor cursor) {
        MenuGroupVO groupVo = new MenuGroupVO();
        groupVo.setGroupID(cursor.getString(cursor.getColumnIndex(MenuGroup.GROUP_ID)));
        groupVo.setGroupName(cursor.getString(cursor.getColumnIndex(MenuGroup.GROUP_NAME)));
        groupVo.setSort(cursor.getInt(cursor.getColumnIndex(MenuGroup.SORT)));
        return groupVo;
    }

    private MenuItemVO createMenu(Cursor cursor) {
        MenuItemVO menu = new MenuItemVO();
        menu.setID(cursor.getString(cursor.getColumnIndex(Menu.MENU_ID)));
        menu.setName(cursor.getString(cursor.getColumnIndex(Menu.MENU_NAME)));
        menu.setIconUrl(cursor.getString(cursor.getColumnIndex(Menu.MENU_ICON)));
        menu.setActionType(cursor.getString(cursor.getColumnIndex(Menu.MENU_ACTION_TYPE)));
        menu.setGroupID(cursor.getString(cursor.getColumnIndex(Menu.MENU_GTOUP_ID)));
        menu.setSort(cursor.getInt(cursor.getColumnIndex(Menu.SORT)));
        return menu;
    }
}
