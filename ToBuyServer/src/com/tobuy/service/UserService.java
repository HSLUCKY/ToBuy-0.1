package com.tobuy.service;

import com.tobuy.pojo.vo.user.UserResp;

public interface UserService {
	/**
	 * 获取消息界面消息发送方的用户信息
	 * @param userResp
	 * @return
	 */
	public abstract UserResp loadUsersInfo(UserResp userResp);
	/**
	 * 通过id查找对应的用户信息
	 * @param userResp
	 * @return
	 */
	public abstract UserResp loadUserById(UserResp userResp);

}

