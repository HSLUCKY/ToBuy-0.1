package com.focustech.tobuy.bean.service.history;

import com.focustech.tobuy.bean.service.base.BaseResp;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/2.
 */

public class SendHistoryListResp extends BaseResp {

    /**
     * 历史记录持有者id
     */
    public int id;

    /**
     * 帖子id 类型  浏览次数
     * 一次性分时间提交
     * 频繁提交会导致网络阻塞
     */
    public HashMap<Integer, HashMap<Integer, Integer>> someHistory;


    public HashMap<Integer, HashMap<Integer, Integer>> getSomeHistory() {
        return someHistory;
    }

    public void setSomeHistory(HashMap<Integer, HashMap<Integer, Integer>> someHistory) {
        this.someHistory = someHistory;
    }
}
