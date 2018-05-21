package com.focustech.tobuy.bean.service.card;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.CardTable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 社区加载商品列表
 * 用户商品表
 * 商品表
 * 用户表
 */

public class GetCardListResp extends BaseResp {

    /**
     * 类型   发布者id   帖子列表
     */
    HashMap<Integer, HashMap<Integer, ArrayList<CardTable>>> cards;



    public HashMap<Integer, HashMap<Integer, ArrayList<CardTable>>> getCards() {
        return cards;
    }

    public void setCards(HashMap<Integer, HashMap<Integer, ArrayList<CardTable>>> cards) {
        this.cards = cards;
    }
}
