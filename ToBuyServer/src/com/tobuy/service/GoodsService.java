package com.tobuy.service;

import java.util.ArrayList;

import com.tobuy.pojo.vo.goods.GetGoodsResp;
import com.tobuy.pojo.vo.goods.SendGoodsResp;
import com.tobuy.pojo.vo.message.SendMessageResp;

public interface GoodsService {
	
	/**
	 * 每次加载商品
	 * @param start
	 * @param count
	 * @return
	 */
	public abstract GetGoodsResp getGoodsByCount(int start, int count);
	/**
	 * 通过标签加载商品
	 * @param type
	 * @param start
	 * @param tips
	 * @return
	 */
	public abstract GetGoodsResp getGoodsByType(int type, int start, ArrayList<String> tips);
	/**
	 * 发送商品留言
	 * @param id
	 * @param sendMessageResp
	 * @return
	 */
	public abstract int sendMessageBySendMessageResp(int id, SendMessageResp sendMessageResp);
	/**
	 * 发布商品
	 * @param sendGoodsResp
	 * @return
	 */
	public abstract SendGoodsResp publishGoods(SendGoodsResp sendGoodsResp);
	
	/**
	 * 通过关键字搜索商品
	 * @param key
	 * @return
	 */
	public abstract GetGoodsResp searchGoodsByKey(String key);
	
}
