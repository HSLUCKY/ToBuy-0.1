package com.tobuy.pojo.vo.card;

import java.util.HashMap;

import com.tobuy.pojo.CardTable;
import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.base.BaseResp;
import com.tobuy.pojo.vo.message.GetMessagesesResp;


public class GetDetailCardResp extends BaseResp {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1061018586758077394L;

	public CardTable cardTable;

    public UserTable userTable;

    public GetMessagesesResp getMessagesesResp;

    public HashMap<Integer, Integer> getMessagesesMap;





    public CardTable getCardTable() {
        return cardTable;
    }

    public void setCardTable(CardTable cardTable) {
        this.cardTable = cardTable;
    }

    public GetMessagesesResp getGetMessagesesResp() {
        return getMessagesesResp;
    }

    public void setGetMessagesesResp(GetMessagesesResp getMessagesesResp) {
        this.getMessagesesResp = getMessagesesResp;
    }

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    public HashMap<Integer, Integer> getGetMessagesesMap() {
        return getMessagesesMap;
    }

    public void setGetMessagesesMap(HashMap<Integer, Integer> getMessagesesMap) {
        this.getMessagesesMap = getMessagesesMap;
    }
}
