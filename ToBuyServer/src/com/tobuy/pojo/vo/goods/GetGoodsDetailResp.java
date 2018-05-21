package com.tobuy.pojo.vo.goods;

import java.util.ArrayList;
import java.util.HashMap;

import com.tobuy.pojo.GoodsTable;
import com.tobuy.pojo.MessageTable;
import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.base.BaseResp;

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
	 * 
	 */
	private static final long serialVersionUID = 3337897773186425309L;

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
