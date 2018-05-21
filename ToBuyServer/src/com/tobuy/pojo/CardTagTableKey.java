package com.tobuy.pojo;

import java.io.Serializable;

public class CardTagTableKey implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8483626266366912950L;

	private Integer cardId;

    private Integer tagId;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}