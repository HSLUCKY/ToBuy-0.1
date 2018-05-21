package com.tobuy.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.tobuy.pojo.MessageTable;
import com.tobuy.pojo.UserMessageTableKey;
import com.tobuy.pojo.vo.message.GetCardsMessageEntity;
import com.tobuy.pojo.vo.message.GetUserMessageEntity;
import com.tobuy.pojo.vo.message.GetUserSendMessageEntity;

public interface UserMessageTableMapper {
    int deleteByPrimaryKey(UserMessageTableKey key);

    int insert(UserMessageTableKey record);

    int insertSelective(UserMessageTableKey record);
    
    /**
     * 用户留言列表
     * @param aid
     * @return
     */
    GetUserMessageEntity getAcceptMessageById(int aid);
    
    /**
     * 帖子留言列表
     * @param aid
     * @return
     */
    GetCardsMessageEntity getCardMessageById(int aid);
    
    
    /**
     * 用户发言列表
     * 只有参照方不同，后期可以使用动态sql简化
     * @param aid
     * @return
     */
    GetUserSendMessageEntity getSendMessageById(int aid);
    
    /**
     * 获取留言列表
     */
    ArrayList<MessageTable> getMessageByChat(HashMap<String, Integer> chat);
    
}