package com.tobuy.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tobuy.constants.ResponseConstants;
import com.tobuy.mapper.CardMessageTableMapper;
import com.tobuy.mapper.GoodsMessageTableMapper;
import com.tobuy.mapper.GoodsResourceTableMapper;
import com.tobuy.mapper.GoodsTableMapper;
import com.tobuy.mapper.MessageTableMapper;
import com.tobuy.mapper.ResourceTableMapper;
import com.tobuy.mapper.UserMessageTableMapper;
import com.tobuy.mapper.UserTableMapper;
import com.tobuy.pojo.CardMessageTableKey;
import com.tobuy.pojo.GoodsMessageTableKey;
import com.tobuy.pojo.GoodsTable;
import com.tobuy.pojo.UserMessageTableKey;
import com.tobuy.pojo.vo.goods.GetGoodsResp;
import com.tobuy.pojo.vo.goods.SendGoodsResp;
import com.tobuy.pojo.vo.message.SendMessageResp;
import com.tobuy.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Resource
	public GoodsTableMapper goodsTableMaper;
	@Resource
	public UserTableMapper userTableMapper;
	@Resource
	public MessageTableMapper messageTableMapper;

	@Resource
	public GoodsMessageTableMapper goodsMessageTableMapper;
	@Resource
	public CardMessageTableMapper cardMessageTableMapper;
	@Resource
	public UserMessageTableMapper userMessageTableMapper;

	@Resource
	public GoodsResourceTableMapper goodsResourceTableMapper;
	@Resource
	public ResourceTableMapper resourceTableMapper;
	
	
	

	
	
	@Override
	public GetGoodsResp getGoodsByType(int type, int start, ArrayList<String> tips) {
		GetGoodsResp getGoodsResp = new GetGoodsResp();
		HashMap<String, Object> params = new HashMap<String, Object>();
		String typeField = "";
		switch (type) {
		case 0:
			typeField = "read_count";
			break;
		case 1:
			typeField = "date";
			break;
		case 2:
			typeField = "price";
			break;
		case -2:
			typeField = "price";
			params.put("start", start);
			params.put("type", typeField);
			params.put("tips", tips);
			getGoodsResp.setGoodsTable(goodsTableMaper.selectGoodsBysortedDESC(params));
			return getGoodsResp;
		case 3:
			ArrayList<String> sTips = new ArrayList<String>();
			tips.add("实惠");
			tips.add("特卖");
			tips.add("减价");
			params.put("start", start);
			params.put("sTips", sTips);
			params.put("tips", tips);
			getGoodsResp.setGoodsTable(goodsTableMaper.selectGoodsBysortedASCByTips(params));
			return getGoodsResp;

		}
		params.put("start", start);
		params.put("type", typeField);
		params.put("tips", tips);
		getGoodsResp.setGoodsTable(goodsTableMaper.selectGoodsBysortedASC(params));
		return getGoodsResp;
	}

	@Override
	public int sendMessageBySendMessageResp(int id, SendMessageResp sendMessageResp) {

		int type = sendMessageResp.getType();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("id", -1);
		params.put("sendMessageResp", sendMessageResp);
		messageTableMapper.insertSelectiveRet(params);
		int key = (Integer) params.get("id");

		switch (type) {
		case 1:
			CardMessageTableKey cardMessageTableKey = new CardMessageTableKey();
			cardMessageTableKey.setFromUserId(sendMessageResp.getFrom_user_id());
			cardMessageTableKey.setToCardId(sendMessageResp.getTo_id());
			cardMessageTableKey.setMessageId(key);
			cardMessageTableMapper.insertSelective(cardMessageTableKey);
			break;
		case 2:
			GoodsMessageTableKey goodsMessageTableKey = new GoodsMessageTableKey();
			goodsMessageTableKey.setFromUserId(sendMessageResp.getFrom_user_id());
			goodsMessageTableKey.setToGoodsId(sendMessageResp.getTo_id());
			goodsMessageTableKey.setMessageId(key);
			goodsMessageTableMapper.insertSelective(goodsMessageTableKey);
			break;
		case 3:
			UserMessageTableKey userMessageTableKey = new UserMessageTableKey();
			userMessageTableKey.setFromUserId(sendMessageResp.getFrom_user_id());
			userMessageTableKey.setToUserId(sendMessageResp.getTo_id());
			userMessageTableKey.setMessageId(key);
			userMessageTableMapper.insertSelective(userMessageTableKey);
			break;
		default:
			break;
		}

		return 0;
	}

	@Override
	public SendGoodsResp publishGoods(SendGoodsResp sendGoodsResp) {
		
		if (goodsTableMaper.insertSelective(sendGoodsResp.goodsTable) == 1) {
			sendGoodsResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
			sendGoodsResp.setRetinfo("发布失败！");
		}else {
			sendGoodsResp.setRetcode(Integer.parseInt(ResponseConstants.FAILED_CODE_END));
		}
		sendGoodsResp.setGoodsTable(null);
		
		return sendGoodsResp;
	}
	
	
	@Override
	public GetGoodsResp getGoodsByCount(int start, int count) {

		HashMap<String, Integer> params = new HashMap<String, Integer>();

		params.put("start", start);
		params.put("count", count);

		GetGoodsResp getGoodsResp = initGetGoodsResp(new GetGoodsResp());

		/**
		 * Get Goods Table
		 */
		initGetGoodsRespData(getGoodsResp, goodsTableMaper.selectGoodsByCount(params));

		return getGoodsResp;
	}
	
	@Override
	public GetGoodsResp searchGoodsByKey(String key) {
		
		GetGoodsResp getGoodsResp = initGetGoodsResp(new GetGoodsResp());
		/**
		 * Get Goods Table
		 */
		initGetGoodsRespData(getGoodsResp, goodsTableMaper.selectGoodsByKey(key));
		return getGoodsResp;
	}

	/**
	 * 初始化商品响应视图
	 * @param getGoodsResp
	 * @return
	 */
	public GetGoodsResp initGetGoodsResp(GetGoodsResp getGoodsResp) {
		getGoodsResp = new GetGoodsResp();
		getGoodsResp.setImages(new ArrayList<ArrayList<byte[]>>());
		return getGoodsResp;
	}
	
	/**
	 * 初始化商品图片数据
	 * @param getGoodsResp
	 * @param goodsTables
	 */
	public void initGetGoodsRespData(GetGoodsResp getGoodsResp, ArrayList<GoodsTable> goodsTables) {
		
		
		if (goodsTables == null) {
			getGoodsResp.setRetcode(Integer.parseInt(ResponseConstants.FAILED_CODE_END));
		} else {
			getGoodsResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
			getGoodsResp.setGoodsTable(goodsTables);

			/**
			 * Get Images Table
			 */
			if (goodsTables != null) {
				
				ArrayList<ArrayList<Integer>> resIdses = new ArrayList<ArrayList<Integer>>();
				for (GoodsTable goodsTable : getGoodsResp.getGoodsTable()) {
					resIdses.add(goodsResourceTableMapper.selectResourseByIds(goodsTable.getId()));
				}

				ArrayList<ArrayList<String>> srcses = new ArrayList<ArrayList<String>>();
				for (ArrayList<Integer> resIds : resIdses) {
					ArrayList<String> srcs = new ArrayList<String>();
					if (!resIds.isEmpty()) {
						srcs = resourceTableMapper.selectSrcByIds(resIds);
					}
					srcses.add(srcs);
				}

				ArrayList<ArrayList<File>> fileses = new ArrayList<ArrayList<File>>();
				for (int i = 0; i < srcses.size(); i++) {
					ArrayList<File> files = new ArrayList<File>();
					for (String src : srcses.get(i)) {
						if (src != null && !"".equals(src)) {
							files.add(new File(src));
						}
					}
					fileses.add(files);
				}
				for (int i = 0; i < fileses.size(); i++) {
					ArrayList<byte[]> images = new ArrayList<byte[]>();
					for (File file : fileses.get(i)) {
						
						BufferedInputStream bis = null;
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						DataOutputStream dos = new DataOutputStream(baos);
						try {
							bis = new BufferedInputStream(new FileInputStream(file));
							byte[] temp = new byte[1024];
							int len = -1;
							while ((len = bis.read(temp)) != -1) {
								dos.write(temp, 0, len);
							}
							baos.flush();
							images.add(baos.toByteArray());
						} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
						try {
							baos.close();
							bis.close();
						} catch (IOException e) {e.printStackTrace();}
						//=================================================================
						
					}
					getGoodsResp.getImages().add(images);
				}
			}
 		
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public GoodsTableMapper getGoodsTableMaper() {
		return goodsTableMaper;
	}

	public void setGoodsTableMaper(GoodsTableMapper goodsTableMaper) {
		this.goodsTableMaper = goodsTableMaper;
	}

	public UserTableMapper getUserTableMapper() {
		return userTableMapper;
	}

	public void setUserTableMapper(UserTableMapper userTableMapper) {
		this.userTableMapper = userTableMapper;
	}

	public MessageTableMapper getMessageTableMapper() {
		return messageTableMapper;
	}

	public void setMessageTableMapper(MessageTableMapper messageTableMapper) {
		this.messageTableMapper = messageTableMapper;
	}

	public GoodsMessageTableMapper getGoodsMessageTableMapper() {
		return goodsMessageTableMapper;
	}

	public void setGoodsMessageTableMapper(GoodsMessageTableMapper goodsMessageTableMapper) {
		this.goodsMessageTableMapper = goodsMessageTableMapper;
	}

	public CardMessageTableMapper getCardMessageTableMapper() {
		return cardMessageTableMapper;
	}

	public void setCardMessageTableMapper(CardMessageTableMapper cardMessageTableMapper) {
		this.cardMessageTableMapper = cardMessageTableMapper;
	}

	public UserMessageTableMapper getUserMessageTableMapper() {
		return userMessageTableMapper;
	}

	public void setUserMessageTableMapper(UserMessageTableMapper userMessageTableMapper) {
		this.userMessageTableMapper = userMessageTableMapper;
	}

	public GoodsResourceTableMapper getGoodsResourceTableMapper() {
		return goodsResourceTableMapper;
	}

	public void setGoodsResourceTableMapper(GoodsResourceTableMapper goodsResourceTableMapper) {
		this.goodsResourceTableMapper = goodsResourceTableMapper;
	}

	public ResourceTableMapper getResourceTableMapper() {
		return resourceTableMapper;
	}

	public void setResourceTableMapper(ResourceTableMapper resourceTableMapper) {
		this.resourceTableMapper = resourceTableMapper;
	}


}
