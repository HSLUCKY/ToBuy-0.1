package com.tobuy.pojo;

import java.io.Serializable;

public class MessageResourceTableKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3497908815693188386L;

	private Integer messageId;

    private Integer resourceId;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}