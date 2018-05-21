package com.focustech.tobuy.bean.service.message;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.MessageTable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 该实体是用于封装多信息接收的情况
 * 类似于用户的消息列表
 * 其余地方一般不直接使用
 */

public class GetMessagesesResp extends BaseResp {
    /**
     * 信息的接收方id
     */
    public int id;
    /**
     * 用于区分是那种主动id  用户商品还是帖子
     * 1    2   3
     */
    public int type;
    /**
     * 发送方和信息的map
     */
    public HashMap<Integer, ArrayList<MessageTable>> messageses;
    /**
     * 发送方和信息的头像
     */
    public HashMap<Integer, byte[]> from_users;




    public HashMap<Integer, byte[]> getFrom_users() {
        return from_users;
    }

    public void setFrom_users(HashMap<Integer, byte[]> from_users) {
        this.from_users = from_users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public HashMap<Integer, ArrayList<MessageTable>> getMessageses() {
        return messageses;
    }

    public void setMessageses(HashMap<Integer, ArrayList<MessageTable>> messageses) {
        this.messageses = messageses;
    }

}
