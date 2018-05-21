package com.tobuy.pojo;

import java.io.Serializable;

public class GoodsResourceTableKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1474269327184985986L;

	private Integer goodsId;

    private Integer resourceId;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}