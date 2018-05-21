package com.focustech.tobuy.bean.table.message;

public class CardMessageTableKey {
    private Integer fromUserId;

    private Integer toCardId;

    private Integer messageId;

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToCardId() {
        return toCardId;
    }

    public void setToCardId(Integer toCardId) {
        this.toCardId = toCardId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}