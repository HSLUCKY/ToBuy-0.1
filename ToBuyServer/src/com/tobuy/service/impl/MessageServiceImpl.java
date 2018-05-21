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
import com.tobuy.mapper.CardTableMapper;
import com.tobuy.mapper.GoodsMessageTableMapper;
import com.tobuy.mapper.GoodsTableMapper;
import com.tobuy.mapper.MessageTableMapper;
import com.tobuy.mapper.UserMessageTableMapper;
import com.tobuy.pojo.CardMessageTableKey;
import com.tobuy.pojo.GoodsMessageTableKey;
import com.tobuy.pojo.MessageTable;
import com.tobuy.pojo.UserMessageTableKey;
import com.tobuy.pojo.vo.message.GetCardsMessageEntity;
import com.tobuy.pojo.vo.message.GetGoodsMessageEntity;
import com.tobuy.pojo.vo.message.GetMessagesesResp;
import com.tobuy.pojo.vo.message.GetUserMessageEntity;
import com.tobuy.pojo.vo.message.GetUserSendMessageEntity;
import com.tobuy.pojo.vo.message.SendMessageResp;
import com.tobuy.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Resource
	public GoodsMessageTableMapper goodsMessageTableMapper;
	@Resource
	public CardMessageTableMapper cardMessageTableMapper;
	@Resource
	public UserMessageTableMapper userMessageTableMapper;
	@Resource
	public MessageTableMapper messageTableMapper;
	@Resource
	public CardTableMapper cardTableMapper;
	@Resource
	public GoodsTableMapper goodsTableMapper;
	
	/**
	 * 获取
	 * 商品留言
	 */
	@Override
	public GetMessagesesResp loadMessageses(GetMessagesesResp getMessagesesResp) {

		initConponent(getMessagesesResp);

		GetGoodsMessageEntity getGoodsMessageEntity = goodsMessageTableMapper.getGoodsMessageByGoods(getMessagesesResp);
		if (getGoodsMessageEntity == null) {
			return getMessagesesResp;
		}

		getMessagesesResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		
		ArrayList<Integer> uid = getGoodsMessageEntity.getUid();
		ArrayList<MessageTable> messages = getGoodsMessageEntity.getMessageTables();
		ArrayList<String> imagesSrc = getGoodsMessageEntity.getImages();
		
		
		return initGetMessagesesResp(getMessagesesResp, uid, messages, imagesSrc);
	}

	/**
	 * 获取
	 * 帖子留言
	 */
	@Override
	public GetMessagesesResp loadCardMessages(GetMessagesesResp getMessagesesResp) {

		initConponent(getMessagesesResp);

		GetCardsMessageEntity getCardsMessageEntity = cardMessageTableMapper.getCardsMessageByGoods(getMessagesesResp);
		if (getCardsMessageEntity == null) {
			return getMessagesesResp;
		}
		getMessagesesResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));

		ArrayList<Integer> uid = getCardsMessageEntity.getUid();
		ArrayList<MessageTable> messages = getCardsMessageEntity.getMessageTables();
		ArrayList<String> imagesSrc = getCardsMessageEntity.getImages();

		return initGetMessagesesResp(getMessagesesResp, uid, messages, imagesSrc);

	}

	/**
	 * 获得
	 * 用户留言
	 */
	@Override
	public GetMessagesesResp loadUserMessages(GetMessagesesResp getMessagesesResp) {

		initConponent(getMessagesesResp);

		GetUserMessageEntity getUserMessageEntity = userMessageTableMapper.getAcceptMessageById(getMessagesesResp.getId());
		if (getUserMessageEntity == null) {
			return getMessagesesResp;
		}
		getMessagesesResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));

		ArrayList<Integer> uid = getUserMessageEntity.getUid();
		ArrayList<MessageTable> messages = getUserMessageEntity.getMessageTables();
		ArrayList<String> imagesSrc = getUserMessageEntity.getImages();

		return initGetMessagesesResp(getMessagesesResp, uid, messages, imagesSrc);

	}
	
	/**
	 * 用户发言
	 */
	@Override
	public GetMessagesesResp loadUserSendMessages(GetMessagesesResp getMessagesesResp) {

		initConponent(getMessagesesResp);

		GetUserSendMessageEntity getUserSendMessageEntity = userMessageTableMapper.getSendMessageById(getMessagesesResp.getId());
		if (getUserSendMessageEntity == null) {
			return getMessagesesResp;
		}
		getMessagesesResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));

		ArrayList<Integer> uid = getUserSendMessageEntity.getTo_user_ids();
		ArrayList<MessageTable> messages = getUserSendMessageEntity.getTo_user_messages();
		ArrayList<String> imagesSrc = getUserSendMessageEntity.getTo_user_images();

		return initGetMessagesesResp(getMessagesesResp, uid, messages, imagesSrc);

	}
	
	
	//============================================================================================
	/**
	 * 根据id进行发送信息
	 */
	@Override
	public SendMessageResp sendMessageByType(SendMessageResp sendMessageResp) {
		
		int type = sendMessageResp.getType();
		MessageTable messageTable = sendMessageResp.getMessageTable();
		if (messageTableMapper.insert(messageTable) != 1) {
			sendMessageResp.setRetcode(Integer.parseInt(ResponseConstants.FAILED_CODE_END));
			sendMessageResp.setMessageTable(null);
			return sendMessageResp;
		}
		switch (type) {
		case 1:
			insertCardMessage(sendMessageResp, messageTable);
			sendMessageResp.setTo_id(getCardUid(sendMessageResp.getTo_id()));
			insertUserMessage(sendMessageResp, messageTable);
			break;
		case 2:
			insertGoodsMessage(sendMessageResp, messageTable);
			sendMessageResp.setTo_id(getGoodsUid(sendMessageResp.getTo_id()));
			insertUserMessage(sendMessageResp, messageTable);
			break;
		case 3:
			insertUserMessage(sendMessageResp, messageTable);
			break;
		default:
			sendMessageResp.setRetcode(Integer.parseInt(ResponseConstants.FAILED_CODE_END));
			return sendMessageResp;
		}
		sendMessageResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		return sendMessageResp;
	}
	
	public boolean insertUserMessage(SendMessageResp sendMessageResp, MessageTable messageTable) {
		
		UserMessageTableKey userMessageTableKey = new UserMessageTableKey();
		userMessageTableKey.setFromUserId(sendMessageResp.getFrom_user_id());
		userMessageTableKey.setToUserId(sendMessageResp.getTo_id());
		userMessageTableKey.setMessageId(messageTable.getId());
		if (userMessageTableMapper.insert(userMessageTableKey) == 1) {
			return true;
		}
		
		return false;
	}
	
	public boolean insertGoodsMessage(SendMessageResp sendMessageResp, MessageTable messageTable) {
		
		GoodsMessageTableKey goodsMessageTableKey = new GoodsMessageTableKey();
		goodsMessageTableKey.setFromUserId(sendMessageResp.getFrom_user_id());
		goodsMessageTableKey.setToGoodsId(sendMessageResp.getTo_id());
		goodsMessageTableKey.setMessageId(messageTable.getId());
		if (goodsMessageTableMapper.insert(goodsMessageTableKey) == 1) {
			return true;
		}
		return false;
	}
	
	public boolean insertCardMessage(SendMessageResp sendMessageResp, MessageTable messageTable) {
		
		CardMessageTableKey cardMessageTableKey = new CardMessageTableKey();
		cardMessageTableKey.setFromUserId(sendMessageResp.getFrom_user_id());
		cardMessageTableKey.setToCardId(sendMessageResp.getTo_id());
		cardMessageTableKey.setMessageId(messageTable.getId());
		if (cardMessageTableMapper.insert(cardMessageTableKey) == 1) {
			return true;
		}
		return false;
	}
	
	public int getCardUid(int cardId){
		return cardTableMapper.getUidByCardId(cardId);
	}
	public int getGoodsUid(int goodsId) {
		return goodsTableMapper.selectUidByGoodsId(goodsId);
	}
	
	//================================================================================================
	
	@Override
	public GetMessagesesResp loadUserMessageByFrom(GetMessagesesResp getMessagesesResp) {
		getMessagesesResp.setMessageses(new HashMap<Integer, ArrayList<MessageTable>>());
		int m_id = getMessagesesResp.getId();
		int t_id = getMessagesesResp.getType();
		HashMap<Integer, ArrayList<MessageTable>> m_t_messages = getMessagesesResp.getMessageses();
		HashMap<String, Integer> chat = new HashMap<String, Integer>();
		
		//=============================================================================
		chat.put("m_id", m_id);
		chat.put("t_id", t_id);
		/**
		 * 我发给对方的信息
		 */
		m_t_messages.put(m_id, userMessageTableMapper.getMessageByChat(chat));
		
		//=============================================================================
		/**
		 * 清空id交换位置
		 */
		chat.clear();
		//=============================================================================
		
		chat.put("m_id", t_id);
		chat.put("t_id", m_id);
		/**
		 * 对方发给我的信息
		 */
		m_t_messages.put(t_id, userMessageTableMapper.getMessageByChat(chat));
		//=============================================================================
		getMessagesesResp.setRetcode(Integer.parseInt(ResponseConstants.SUCESS_CODE_END));
		
		
		getMessagesesResp.setMessageses(m_t_messages);
		
		return getMessagesesResp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ============================================================================
	/**
	 * 抽象公共初始化信息响应模型
	 * 
	 */
	public GetMessagesesResp initGetMessagesesResp(GetMessagesesResp getMessagesesResp, ArrayList<Integer> uid,
			ArrayList<MessageTable> messages, ArrayList<String> imagesSrc) {

		ArrayList<byte[]> images = new ArrayList<byte[]>();
		for (String src : imagesSrc) {
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(src)));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				DataOutputStream dos = new DataOutputStream(baos);
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = bis.read(buffer)) != -1) {
					dos.write(buffer, 0, len);
				}
				dos.flush();
				images.add(baos.toByteArray());
				baos.close();
				dos.close();
				bis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < uid.size(); i++) {
			getMessagesesResp.getMessageses().put(uid.get(i), new ArrayList<MessageTable>());
			getMessagesesResp.getFrom_users().put(uid.get(i), images.get(i));
		}
		for (int i = 0; i < uid.size(); i++) {
			getMessagesesResp.getMessageses().get(uid.get(i)).add(messages.get(i));
		}

		return getMessagesesResp;

	}

	public void initConponent(GetMessagesesResp getMessagesesResp) {
		getMessagesesResp.setMessageses(new HashMap<Integer, ArrayList<MessageTable>>());
		getMessagesesResp.setFrom_users(new HashMap<Integer, byte[]>());
	}
}
