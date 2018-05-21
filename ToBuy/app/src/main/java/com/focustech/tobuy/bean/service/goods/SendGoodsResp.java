package com.focustech.tobuy.bean.service.goods;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;

/**
 * Created by Administrator on 2018/5/2.
 */

public class SendGoodsResp extends BaseResp {
    /**
     * 用户id
     */
    public int id;
    /**
     * 商品实体
     */
    public GoodsTable goodsTable;



    public GoodsTable getGoodsTable() {
        return goodsTable;
    }

    public void setGoodsTable(GoodsTable goodsTable) {
        this.goodsTable = goodsTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
