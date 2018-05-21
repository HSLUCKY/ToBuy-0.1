package com.tobuy.mapper;

import com.tobuy.pojo.MessageResourceTableKey;

public interface MessageResourceTableMapper {
    int deleteByPrimaryKey(MessageResourceTableKey key);

    int insert(MessageResourceTableKey record);

    int insertSelective(MessageResourceTableKey record);
}