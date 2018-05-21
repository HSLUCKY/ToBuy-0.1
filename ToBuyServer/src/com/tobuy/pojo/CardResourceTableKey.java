package com.tobuy.pojo;

import java.io.Serializable;

public class CardResourceTableKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6269321808571007610L;

	private Integer cardId;

    private Integer resourceId;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}