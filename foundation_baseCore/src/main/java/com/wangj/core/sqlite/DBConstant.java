package com.wangj.core.sqlite;

public class DBConstant {

    /** 数据库名 */
    public static final String DB_NAME = "haha";

    /**
     * 菜单组
     */
    public static class MenuGroup {
        /** 表名 */
        public static final String TABLE_MAME = "tab_menu_group";

        public static final String GROUP_ID = "groupID";
        public static final String GROUP_NAME = "groupName";
        public static final String SORT = "sort";
    }

    /**
     * 菜单项
     */
    public static class Menu {
        /** 表名 */
        public static final String TABLE_MAME = "tab_menu";

        public static final String MENU_ID = "menuID";
        public static final String MENU_NAME = "menuName";
        public static final String MENU_ACTION_TYPE = "actionType";
        public static final String MENU_ICON = "iconUrl";
        public static final String MENU_GTOUP_ID = "groupID";
        public static final String SORT = "sort";
    }
}
