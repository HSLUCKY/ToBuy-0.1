package com.focustech.tobuy.bean.service.goods;

import com.focustech.tobuy.bean.service.base.BaseResp;
import com.focustech.tobuy.bean.table.entity.GoodsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/1.
 */

public class GetGoodsResp extends BaseResp {

    public ArrayList<GoodsTable> goodsTable;
    public ArrayList<ArrayList<byte[]>> images;

    public ArrayList<GoodsTable> getGoodsTable() {
        return goodsTable;
    }

    public void setGoodsTable(ArrayList<GoodsTable> goodsTable) {
        this.goodsTable = goodsTable;
    }

    public ArrayList<ArrayList<byte[]>> getImages() {
        return images;
    }

    public void setImages(ArrayList<ArrayList<byte[]>> images) {
        this.images = images;
    }

}
