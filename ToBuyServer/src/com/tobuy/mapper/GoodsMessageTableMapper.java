package com.tobuy.mapper;

import com.tobuy.pojo.GoodsMessageTableKey;
import com.tobuy.pojo.vo.message.GetGoodsMessageEntity;
import com.tobuy.pojo.vo.message.GetMessagesesResp;

public interface GoodsMessageTableMapper {
    int deleteByPrimaryKey(GoodsMessageTableKey key);

    int insert(GoodsMessageTableKey record);

    int insertSelective(GoodsMessageTableKey record);
    
    GetGoodsMessageEntity getGoodsMessageByGoods(GetMessagesesResp getMessagesesResp);

    
}