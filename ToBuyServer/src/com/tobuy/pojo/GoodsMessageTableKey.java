package com.tobuy.pojo;

import java.io.Serializable;

public class GoodsMessageTableKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3982407394245959437L;

	private Integer fromUserId;

    private Integer toGoodsId;

    private Integer messageId;

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToGoodsId() {
        return toGoodsId;
    }

    public void setToGoodsId(Integer toGoodsId) {
        this.toGoodsId = toGoodsId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}