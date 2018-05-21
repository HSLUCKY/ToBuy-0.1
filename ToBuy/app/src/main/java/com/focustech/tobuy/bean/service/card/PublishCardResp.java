package com.focustech.tobuy.bean.service.card;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.CardTable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/5/2.
 */

public class PublishCardResp extends BaseResp {

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
