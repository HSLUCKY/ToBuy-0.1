package com.focustech.tobuy.bean.service.goods;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;
import com.focustech.tobuy.bean.table.entity.MessageTable;
import com.focustech.tobuy.bean.table.entity.UserTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  从
 *  用户商品表
 *  用户表
 *  商品表
 *
 *  商品信息表
 *  信息表
 *  商品表
 *  中获取资源
 */

public class GetGoodsDetailResp extends BaseResp {
    /**
     * 商品信息实体
     */
    public GoodsTable goodsTable;

    /**
     * 留言信息
     */
    public HashMap<UserTable, ArrayList<MessageTable>> simpleMessage;



    public GoodsTable getGoodsTable() {
        return goodsTable;
    }

    public void setGoodsTable(GoodsTable goodsTable) {
        this.goodsTable = goodsTable;
    }

    public HashMap<UserTable, ArrayList<MessageTable>> getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(HashMap<UserTable, ArrayList<MessageTable>> simpleMessage) {
        this.simpleMessage = simpleMessage;
    }
}
