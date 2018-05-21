package com.tobuy.pojo.vo.user;

import com.tobuy.pojo.vo.base.BaseResp;

public class GetUserMessageListResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6690200363879621904L;
	public int to_user_id;
	
	

	public int getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(int to_user_id) {
		this.to_user_id = to_user_id;
	}

}
