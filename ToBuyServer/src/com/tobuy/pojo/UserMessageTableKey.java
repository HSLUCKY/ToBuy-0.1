package com.tobuy.pojo;

import java.io.Serializable;

public class UserMessageTableKey  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1595719030236017174L;

	private Integer fromUserId;

    private Integer toUserId;

    private Integer messageId;

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}