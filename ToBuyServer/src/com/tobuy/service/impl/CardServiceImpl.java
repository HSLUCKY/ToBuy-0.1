package com.tobuy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tobuy.constants.ResponseConstants;
import com.tobuy.mapper.CardResourceTableMapper;
import com.tobuy.mapper.CardTableMapper;
import com.tobuy.mapper.CardTagTableMapper;
import com.tobuy.mapper.ResourceTableMapper;
import com.tobuy.pojo.CardTable;
import com.tobuy.pojo.UserTable;
import com.tobuy.pojo.vo.card.GetSimpleCardList;
import com.tobuy.pojo.vo.card.GetSimpleCardListResp;
import com.tobuy.pojo.vo.card.PublishCardResp;
import com.tobuy.service.CardService;
import com.tobuy.util.GetBytesUtil;

@Service
public class CardServiceImpl implements CardService {

	@Resource
	CardTableMapper cardTableMapper;
	@Resource
	CardResourceTableMapper cardResourceTableMapper;
	@Resource
	ResourceTableMapper resourceTableMapper;
	@Resource
	CardTagTableMapper cardTagTableMapper;

	/**
	 * 加载帖子列表
	 */
	@Override
	public GetSimpleCardListResp getSimpleCardList(int start, int count, int type) {

		if (type == 1) {
			System.out.println();
		}

		GetSimpleCardListResp getSimpleCardListResp = new GetSimpleCardListResp();

		/**
		 * 通过起点和终点返回需要的帖子和用户对应Entry
		 */
		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("start", start);
		params.put("count", count);
		params.put("type", type);
		GetSimpleCardList getSimpleCardList = cardTableMapper.getSimpleCardByCount(params);

		if (getSimpleCardList.by_users == null) {
			return getSimpleCardListResp;
		}
		/**
		 * 获得用户和帖子的对应ArrayList
		 */
		ArrayList<UserTable> users = getSimpleCardList.getBy_users();
		ArrayList<CardTable> cards = getSimpleCardList.getCards();

		// =============================================================================
		// =============================用户表去重==============================
		for (int i = 0; i < users.size() - 1; i++) {
			for (int j = 0; j < users.size(); j++) {
				if (i != j) {
					if (users.get(i).getId() == users.get(j).getId()) {
						users.remove(j);
					}

				}
			}
		}

		// =============================================================================
		// ================================封装用户头像=================================
		/**
		 * 通过用户id获取头像资源路径 获取完毕头像的资源顺序和用户列表顺序对应 此过程将用户信息和头像封装完毕
		 */
		for (int i = 0; i < users.size(); i++) {
			String headSrc = resourceTableMapper.selectSrcById(users.get(i).getHead());
			GetSimpleCardListResp.BaseUserInfo baseUserInfo = getSimpleCardListResp.new BaseUserInfo(users.get(i));
			baseUserInfo.setUserHead(GetBytesUtil.getByteBySrc(headSrc));
			getSimpleCardListResp.getBaseUserInfos().add(baseUserInfo);
		}

		// =============================================================================
		// =========================将用户和帖子相对应========================================
		/**
		 * 外层用户 内层帖子 每次遍历帖子遇到和外层用户相同id的就往第外层个帖子列表里添加对应的帖子 此操作完毕用户次序和帖子次序对应
		 */
		ArrayList<ArrayList<CardTable>> cardLists = getSimpleCardListResp.getCardsLists();
		for (int i = 0; i < users.size(); i++) {
			cardLists.add(new ArrayList<CardTable>());
			for (int j = 0; j < cards.size(); j++) {
				if (users.get(i).getId() == cards.get(j).getUserId()) {
					cardLists.get(i).add(cards.get(j));
				}
			}
		}

		// =============================================================================
		// =================================封装帖子资源===============================

		/**
		 * TODO 这里还要拼装获取回来的标签列表 public ArrayList<ArrayList<ArrayList<String>>>
		 * cardsListTags;
		 * 
		 * 
		 * 通过每个帖子去获取帖子对应的资源列表id 此时资源资源列表的顺序和帖子的顺序是相对应的 此操作完毕帖子次序和帖子资源次序对应
		 */
		ArrayList<ArrayList<ArrayList<Integer>>> srcsIdses = new ArrayList<ArrayList<ArrayList<Integer>>>();

		for (int i = 0; i < cardLists.size(); i++) {

			srcsIdses.add(new ArrayList<ArrayList<Integer>>());
			for (int j = 0; j < cardLists.get(i).size(); j++) {

				srcsIdses.get(i).add(new ArrayList<Integer>());

				ArrayList<Integer> srcs = cardResourceTableMapper.getResourcesByCardId(cardLists.get(i).get(j).getId());
				srcsIdses.get(i).get(j).addAll(srcs);

			}
		}

		/**
		 * 通过资源列表id获得对应的文件字节码列表
		 */
		ArrayList<ArrayList<ArrayList<byte[]>>> srcses = getSimpleCardListResp.getCardsListRes();
		for (int i = 0; i < srcsIdses.size(); i++) {

			srcses.add(new ArrayList<ArrayList<byte[]>>());
			for (int j = 0; j < srcsIdses.get(i).size(); j++) {

				srcses.get(i).add(new ArrayList<byte[]>());
				for (int k = 0; k < srcsIdses.get(i).get(j).size(); k++) {

					String src = resourceTableMapper.selectSrcById(srcsIdses.get(i).get(j).get(k));
					srcses.get(i).get(j).add(GetBytesUtil.getByteBySrc(src));
				}
			}
		}
		getSimpleCardListResp.setCardsListRes(srcses);
		// =============================================================================

		getSimpleCardListResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		return getSimpleCardListResp;
	}

	/**
	 * 发布帖子
	 */
	@Override
	public PublishCardResp publishCard(PublishCardResp publishCardResp) {
		CardTable cardTable = publishCardResp.getCardTable();
		if (cardTableMapper.insertSelective(cardTable) == 1) {
			publishCardResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		} else {
			publishCardResp.setRetcode(Integer.parseInt(ResponseConstants.FAILED_CODE_END));
			publishCardResp.setRetinfo("上传失败");
		}

		/**
		 * 帖子标签表中加入关联
		 */
		HashMap<String, Object> entity = new HashMap<String, Object>();
		ArrayList<Integer> tagIds = publishCardResp.getTagIds();
		entity.put("fid", cardTable.getId());
		entity.put("tags", tagIds);
		cardTagTableMapper.insertCardByTagIds(entity);

		publishCardResp.setCardTable(null);

		return publishCardResp;
	}
}
