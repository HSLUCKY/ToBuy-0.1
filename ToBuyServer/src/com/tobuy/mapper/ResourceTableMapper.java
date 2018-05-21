package com.tobuy.mapper;

import java.util.ArrayList;

import com.tobuy.pojo.ResourceTable;

public interface ResourceTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResourceTable record);

    int insertSelective(ResourceTable record);

    ResourceTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResourceTable record);

    int updateByPrimaryKey(ResourceTable record);
    
    /**
     * 通过用户头像idList获得路径列表
     * 多对多
     * @param resIds
     * @return
     */
    ArrayList<String> selectSrcByIds(ArrayList<Integer> resIds);
    
    /**
     * 通过id查找单条记录
     * 一对多
     */
    String selectSrcById(Integer id);
    
    /**
     * 
     */
    String selectSrcByUserId(Integer id);

}