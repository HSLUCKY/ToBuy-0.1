package com.tobuy.mapper;

import com.tobuy.pojo.GoodsHistoryTableKey;

public interface GoodsHistoryTableMapper {
    int deleteByPrimaryKey(GoodsHistoryTableKey key);

    int insert(GoodsHistoryTableKey record);

    int insertSelective(GoodsHistoryTableKey record);
}