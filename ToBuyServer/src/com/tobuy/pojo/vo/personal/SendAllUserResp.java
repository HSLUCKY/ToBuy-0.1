package com.tobuy.pojo.vo.personal;

import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class SendAllUserResp extends BaseResp {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4626544832669896184L;
	public UserTable userTable;

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }
}
