package com.wangj.core.menu;

import com.wangj.core.entity.BaseVO;

public class MenuGroupVO extends BaseVO {
    private String groupID;
    private String groupName;
    private int sort;

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
