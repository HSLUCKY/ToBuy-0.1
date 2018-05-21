package com.tobuy.pojo.vo.login;

import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class LoginResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1503118159616401948L;
	private UserTable userTable;


	public UserTable getUserTable() {
		return userTable;
	}

	public void setUserTable(UserTable userTable) {
		this.userTable = userTable;
	}

}
