package com.tobuy.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.tobuy.pojo.CardTagTableKey;
import com.tobuy.pojo.TagTable;

public interface CardTagTableMapper {
    int deleteByPrimaryKey(CardTagTableKey key);

    int insert(CardTagTableKey record);

    int insertSelective(CardTagTableKey record);
    
    /**
     * 通过帖子id返回对应的标签列表
     * @param bid
     * @return
     */
    ArrayList<TagTable> getTagById(int bid);
    
    /**
     * 插入帖子标签关联
     */
    Integer insertCardByTagIds(HashMap<String, Object> entity);
}