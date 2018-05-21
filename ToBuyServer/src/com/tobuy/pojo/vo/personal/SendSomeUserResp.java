package com.tobuy.pojo.vo.personal;

import java.util.HashMap;

import com.tobuy.pojo.vo.base.BaseResp;

public class SendSomeUserResp extends BaseResp {

    /**
	 * 
	 */
	private static final long serialVersionUID = 575038854642773042L;
	/**
     * 用户字段值键值对
     */
    public HashMap<String, Object> fields;

    public HashMap<String, Object> getFields() {
        return fields;
    }

    public void setFields(HashMap<String, Object> fields) {
        this.fields = fields;
    }
}
