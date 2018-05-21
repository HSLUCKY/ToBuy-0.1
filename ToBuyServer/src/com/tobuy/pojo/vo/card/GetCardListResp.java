package com.tobuy.pojo.vo.card;

import java.util.ArrayList;
import java.util.HashMap;

import com.tobuy.pojo.CardTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class GetCardListResp extends BaseResp {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9067328235900219940L;
	HashMap<Integer, HashMap<Integer, ArrayList<CardTable>>> cards;



    public HashMap<Integer, HashMap<Integer, ArrayList<CardTable>>> getCards() {
        return cards;
    }

    public void setCards(HashMap<Integer, HashMap<Integer, ArrayList<CardTable>>> cards) {
        this.cards = cards;
    }
}
