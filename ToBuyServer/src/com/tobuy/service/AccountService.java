package com.tobuy.service;

import java.util.HashMap;

import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.login.LoginResp;
import com.tobuy.pojo.vo.user.UserResp;

public interface AccountService {
	
	public abstract int add(UserTable userTable) throws Exception;
	public abstract LoginResp getUserTableByLogin(HashMap<String, String> token) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param userTable
	 * @return
	 */
	public abstract UserResp updateAccount(UserTable userTable);
}
