package com.tobuy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tobuy.constants.ResponseConstants;
import com.tobuy.mapper.UserTableMapper;
import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.user.UserResp;
import com.tobuy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserTableMapper userTableMapper;
	
	/**
	 * 返回所有发送方用户信息
	 */
	@Override
	public UserResp loadUsersInfo(UserResp userResp) {
		ArrayList<UserTable> userTables = userTableMapper.loadUsersByToId(userResp.getUid());
		if (userTables == null || userTables.size() == 0) {
			userResp.setRetcode(Integer.parseInt(ResponseConstants.FAILED_CODE_END));
			return userResp;
		}else {
			userResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		}
		
		HashMap<Integer, UserTable> uHashMap = new HashMap<Integer, UserTable>();
		for (UserTable userTable : userTables) {
			uHashMap.put(userTable.getId(), userTable);
		}
		
		userResp.setUsers(uHashMap);
		
		
		return userResp;
	}

	@Override
	public UserResp loadUserById(UserResp userResp) {
		
		
		UserTable userTable =userTableMapper.selectByPrimaryKey(userResp.getUid());
		if (userTable == null) {
			userResp.setRetcode(Integer.parseInt(ResponseConstants.FAILED_CODE_END));
			return userResp;
		}
		
		
		HashMap<Integer, UserTable> userHashMap = new HashMap<Integer, UserTable>();
		userHashMap.put(userTable.getId(), userTable);
		userResp.setUsers(userHashMap);
		userResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		return userResp;
	}
	

	
	
}
