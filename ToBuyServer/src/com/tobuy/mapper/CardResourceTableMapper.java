package com.tobuy.mapper;

import java.util.ArrayList;

import com.tobuy.pojo.CardResourceTableKey;

public interface CardResourceTableMapper {
    int deleteByPrimaryKey(CardResourceTableKey key);

    int insert(CardResourceTableKey record);

    int insertSelective(CardResourceTableKey record);
    
    /**
     * 通过id获得资源列表
     * @param ids
     * @return
     */
    ArrayList<Integer> getResourcesByCardId(Integer cid);
}