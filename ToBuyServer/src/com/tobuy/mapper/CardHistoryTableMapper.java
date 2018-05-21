package com.tobuy.mapper;

import com.tobuy.pojo.CardHistoryTableKey;

public interface CardHistoryTableMapper {
    int deleteByPrimaryKey(CardHistoryTableKey key);

    int insert(CardHistoryTableKey record);

    int insertSelective(CardHistoryTableKey record);
}