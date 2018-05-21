package com.tobuy.pojo;

import java.io.Serializable;

public class CardHistoryTableKey implements Serializable {
	private static final long serialVersionUID = -1904660103930273012L;

	private Integer userId;

    private Integer cardId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}