package com.tobuy.mapper;

import java.util.ArrayList;

import com.tobuy.pojo.GoodsResourceTableKey;

public interface GoodsResourceTableMapper {
    int deleteByPrimaryKey(GoodsResourceTableKey key);

    int insert(GoodsResourceTableKey record);

    int insertSelective(GoodsResourceTableKey record);
    
    ArrayList<Integer> selectResourseByIds(Integer goods_id);
}