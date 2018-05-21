package com.tobuy.pojo;

import java.io.Serializable;

public class CardMessageTableKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1222570397585950468L;

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