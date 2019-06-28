package com.wangj.core.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wangj.core.sqlite.DBConstant.MenuGroup;
import com.wangj.core.sqlite.DBConstant.Menu;

import com.wangj.core.util.LogUtil;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = DBOpenHelper.class.getSimpleName();

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建菜单组表
        String sqlMenuGroup = "CREATE TABLE ".concat(MenuGroup.TABLE_MAME).concat("(")
                .concat(MenuGroup.GROUP_ID).concat(" TEXT PRIMARY KEY,")
                .concat(MenuGroup.GROUP_NAME).concat(" TEXT,")
                .concat(MenuGroup.SORT).concat(" INTEGER")
                .concat(")");
        LogUtil.d(TAG, "菜单组表创建：" + sqlMenuGroup);
        db.execSQL(sqlMenuGroup);

        // 创建菜单项表
        String sqlMenu = "CREATE TABLE ".concat(Menu.TABLE_MAME).concat("(")
                .concat(Menu.MENU_ID).concat(" TEXT PRIMARY KEY,")
                .concat(Menu.MENU_NAME).concat(" TEXT,")
                .concat(Menu.MENU_ICON).concat(" TEXT,")
                .concat(Menu.MENU_ACTION_TYPE).concat(" TEXT,")
                .concat(Menu.MENU_GTOUP_ID).concat(" TEXT,")
                .concat(Menu.SORT).concat(" INTEGER")
                .concat(")");
        LogUtil.d(TAG, "菜单项表创建：" + sqlMenu);
        db.execSQL(sqlMenu);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtil.d(TAG, "DBOpenHelper onUpgrade{oldVersion:" + oldVersion + "; newVersion:" + newVersion + "}");
    }
}
