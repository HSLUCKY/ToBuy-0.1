package com.focustech.tobuy.bean.service.message;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.MessageTable;

/**
 * 首先插入消息得到消息主键
 * 然后建立起点——终点——message关联
 *
 * from_user_id 消息都是用户发送的
 * to_id    可能是帖子商品或是用户
 * type     用于区分帖子商品用户  1帖子 2商品 3用户
 * messageTable 用于添加信息表
 */

public class SendMessageResp extends BaseResp {

    public int from_user_id;
    public int to_id;
    public int type;
    public MessageTable messageTable;

    public int getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public MessageTable getMessageTable() {
        return messageTable;
    }

    public void setMessageTable(MessageTable messageTable) {
        this.messageTable = messageTable;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
