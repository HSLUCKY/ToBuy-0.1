package com.tobuy.pojo;

import java.io.Serializable;

public class GoodsHistoryTableKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7339617913905267422L;

	private Integer userId;

    private Integer goodsId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}