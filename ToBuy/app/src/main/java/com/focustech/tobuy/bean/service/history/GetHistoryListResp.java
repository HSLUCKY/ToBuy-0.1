package com.focustech.tobuy.bean.service.history;

import com.focustech.tobuy.bean.service.base.BaseResp;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/2.
 */

public class GetHistoryListResp extends BaseResp {
    /**
     * 历史持有者id
     */
    public int id;
    /**
     * 确定是哪种类型的帖子
     * 商品或者其他版区的帖子
     * 键是帖子类型：商品或者其他版区的帖子
     * 值是Object用于接收多类型强转
     */
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
