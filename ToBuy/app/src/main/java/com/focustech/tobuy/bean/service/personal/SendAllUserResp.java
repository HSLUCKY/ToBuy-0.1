package com.focustech.tobuy.bean.service.personal;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.UserTable;

/**
 * Created by Administrator on 2018/5/2.
 */

public class SendAllUserResp extends BaseResp {

    public UserTable userTable;

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }
}
