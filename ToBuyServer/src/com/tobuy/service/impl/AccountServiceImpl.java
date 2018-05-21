package com.tobuy.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tobuy.constants.ResponseConstants;
import com.tobuy.mapper.UserTableMapper;
import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.login.LoginResp;
import com.tobuy.pojo.vo.user.UserResp;
import com.tobuy.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {

	@Resource
	private UserTableMapper userTableMapper;
	
	@Override
	public int add(UserTable userTable) throws Exception {
		return userTableMapper.insertSelective(userTable);
	}

	@Override
	public LoginResp getUserTableByLogin(HashMap<String, String> token) throws Exception {
		UserTable userTable = userTableMapper.selectByLogin(token);
		
		LoginResp loginResp = new LoginResp();
		if (userTable != null) {
			loginResp.setUserTable(userTable);
			loginResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		}else {
			loginResp.setRetcode(0);
		}
		
		return loginResp;
	}
	
	
	@Override
	public UserResp updateAccount(UserTable userTable) {
		
		UserResp userResp = new UserResp();
		HashMap<Integer, UserTable> uHashMap = new HashMap<Integer, UserTable>();
		uHashMap.put(userTable.getId(), userTable);
		userResp.setUid(userTable.getId());
		userResp.setUsers(uHashMap);
		if (userTableMapper.updateAccount(userTable) == 1) {
			userResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		}else {
			userResp.setRetcode(Integer.parseInt(ResponseConstants.FAILED_CODE_END));
		}
		
		
		return userResp;
	}

	

	public UserTableMapper getUserTableMapper() {
		return userTableMapper;
	}

	public void setUserTableMapper(UserTableMapper userTableMapper) {
		this.userTableMapper = userTableMapper;
	}




}
