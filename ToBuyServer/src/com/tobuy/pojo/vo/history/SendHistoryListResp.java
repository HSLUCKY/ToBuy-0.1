package com.tobuy.pojo.vo.history;

import java.util.HashMap;

import com.tobuy.pojo.vo.base.BaseResp;

public class SendHistoryListResp extends BaseResp {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8573182480949327613L;

	public int id;

    public HashMap<Integer, HashMap<Integer, Integer>> someHistory;


    public HashMap<Integer, HashMap<Integer, Integer>> getSomeHistory() {
        return someHistory;
    }

    public void setSomeHistory(HashMap<Integer, HashMap<Integer, Integer>> someHistory) {
        this.someHistory = someHistory;
    }
}
