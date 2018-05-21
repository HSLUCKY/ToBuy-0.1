package com.tobuy.pojo.vo.goods;

import com.tobuy.pojo.GoodsTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class SendGoodsResp extends BaseResp {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1717447939272780882L;
	public int id;
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
