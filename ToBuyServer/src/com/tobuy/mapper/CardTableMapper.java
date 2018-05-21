package com.tobuy.mapper;

import java.util.HashMap;

import com.tobuy.pojo.CardTable;
import com.tobuy.pojo.vo.card.GetSimpleCardList;

public interface CardTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CardTable record);

    int insertSelective(CardTable record);

    CardTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CardTable record);

    int updateByPrimaryKeyWithBLOBs(CardTable record);

    int updateByPrimaryKey(CardTable record);
    
    /**
     *	获取帖子和用户
     * 
     * @param params
     * @return
     */
    GetSimpleCardList getSimpleCardByCount(HashMap<String, Integer> params);
    
    /**
     * 获得帖子的用户id
     * @param cid
     * @return
     */
    Integer getUidByCardId(int cid);
    
    
}