package com.tobuy.pojo.vo.user;

import java.util.HashMap;

import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.base.BaseResp;

/**
 * Created by Administrator on 2018/5/2.
 */

public class UserResp extends BaseResp {


    /**
	 * 
	 */
	private static final long serialVersionUID = -5509710632548753823L;

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
