package com.focustech.tobuy.bean.service.card;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.service.message.GetMessagesesResp;
import com.focustech.tobuy.bean.table.entity.CardTable;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.bean.table.entity.UserTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 获取具体帖子的信息
 */
public class GetDetailCardResp extends BaseResp {

    /**
     * 表实体
     */
    public CardTable cardTable;

    /**
     * 发帖用户实体
     */
    public UserTable userTable;

    /**
     * 帖子留言
     * 此表视图里有具体的发送方和消息列表属于重量级
     * 所以帖子加载需要缓存（后面优化）
     * 该参数可选
     */
    public GetMessagesesResp getMessagesesResp;

    /**
     * 轻量级的帖子加载
     * 查询用户信息表 和 帖子信息表
     * 得出每个用户帖子留言的视图
     */
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
