package com.focustech.tobuy.bean.service.login;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.UserTable;

/**
 * <功能详细描述>
 *
 * 具体的在网络请求中接收到的数据体的类
 *
  */
public class LoginResp extends BaseResp {

    private UserTable userTable;

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }
}
