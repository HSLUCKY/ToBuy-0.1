package com.tobuy.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.tobuy.pojo.GoodsTable;

public interface GoodsTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsTable record);

    int insertSelective(GoodsTable record);
    
    int updateByPrimaryKeySelective(GoodsTable record);

    int updateByPrimaryKey(GoodsTable record);
    

    GoodsTable selectByPrimaryKey(Integer id);
    
    
    ArrayList<GoodsTable> selectGoodsByCount(HashMap<String, Integer> params);
    ArrayList<GoodsTable> selectGoodsBysortedASC(HashMap<String, Object> params);
    ArrayList<GoodsTable> selectGoodsBysortedDESC(HashMap<String, Object> params);
    ArrayList<GoodsTable> selectGoodsBysortedASCByTips(HashMap<String, Object> params);

    int selectUidByGoodsId(int gid);
    
    /**
     * 通过关键词搜索商品
     * @param key
     * @return
     */
    ArrayList<GoodsTable> selectGoodsByKey(@Param(value="key") String key);
    
}