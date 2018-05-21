package com.tobuy.pojo.vo.history;

import java.util.HashMap;

import com.tobuy.pojo.vo.base.BaseResp;

public class GetHistoryListResp extends BaseResp {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9158208748241277572L;

	public int id;

    public HashMap<Integer, Object> entitys;

    public HashMap<Integer, Object> getEntitys() {
        return entitys;
    }

    public void setEntitys(HashMap<Integer, Object> entitys) {
        this.entitys = entitys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
