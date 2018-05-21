package com.tobuy.pojo.vo.goods;

import java.util.ArrayList;

import com.tobuy.pojo.GoodsTable;
import com.tobuy.pojo.vo.base.BaseResp;

public class GetGoodsResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3583988186596937258L;
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
