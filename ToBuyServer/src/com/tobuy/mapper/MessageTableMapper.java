package com.tobuy.mapper;

import java.util.HashMap;

import com.tobuy.pojo.MessageTable;

public interface MessageTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageTable record);

    int insertSelective(MessageTable record);

    int insertSelectiveRet(HashMap<String, Object> params);
    
    MessageTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageTable record);

    int updateByPrimaryKeyWithBLOBs(MessageTable record);

    int updateByPrimaryKey(MessageTable record);
    
   
    
}