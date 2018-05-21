package com.focustech.tobuy.bean.service.user;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.UserTable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/2.
 */

public class UserResp extends BaseResp {

    public int uid;

    /**
     * 用户列表
     * key  从参数传入
     */
    public HashMap<Integer, UserTable> users;


    public HashMap<Integer, UserTable> getUsers() {
        return users;
    }

    public void setUsers(HashMap<Integer, UserTable> users) {
        this.users = users;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
