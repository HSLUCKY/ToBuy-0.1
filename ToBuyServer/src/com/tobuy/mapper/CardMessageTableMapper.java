package com.tobuy.mapper;

import com.tobuy.pojo.CardMessageTableKey;
import com.tobuy.pojo.vo.message.GetCardsMessageEntity;
import com.tobuy.pojo.vo.message.GetMessagesesResp;

public interface CardMessageTableMapper {
    int deleteByPrimaryKey(CardMessageTableKey key);

    int insert(CardMessageTableKey record);

    int insertSelective(CardMessageTableKey record);
    
    /**
     * 获得用户留言列表
     * @param getDetailCardSimpleResp
     * @return
     */
    GetCardsMessageEntity getCardsMessageByGoods(GetMessagesesResp getMessagesesResp);
}