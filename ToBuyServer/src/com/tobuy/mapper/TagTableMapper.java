package com.tobuy.mapper;

import java.util.ArrayList;

import com.tobuy.pojo.TagTable;

public interface TagTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TagTable record);

    int insertSelective(TagTable record);

    TagTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TagTable record);

    int updateByPrimaryKey(TagTable record);
    
    ArrayList<TagTable> getAllTags();
} 