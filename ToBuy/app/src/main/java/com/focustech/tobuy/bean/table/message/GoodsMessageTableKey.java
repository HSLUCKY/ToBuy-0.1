package com.focustech.tobuy.bean.table.message;

public class GoodsMessageTableKey {
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