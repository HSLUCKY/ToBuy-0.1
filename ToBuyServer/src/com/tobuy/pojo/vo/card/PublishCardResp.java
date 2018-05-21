package com.tobuy.pojo.vo.card;

import java.util.ArrayList;

import com.tobuy.pojo.CardTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class PublishCardResp extends BaseResp {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8583932802736538244L;

	/**
     * 发送者id    用于帖子的外键
     */
    public int id;

    /**
     * 帖子表实体
     */
    public CardTable cardTable;

    /**
     * 标签id列表
     * @return
     */
    public ArrayList<Integer> tagIds;


    public CardTable getCardTable() {
        return cardTable;
    }

    public void setCardTable(CardTable cardTable) {
        this.cardTable = cardTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(ArrayList<Integer> tagIds) {
        this.tagIds = tagIds;
    }
}
