package com.tobuy.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.tobuy.pojo.UserTable;

public interface UserTableMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTable record);

    int insertSelective(UserTable record);

    UserTable selectByPrimaryKey(Integer id);
    
    UserTable selectByLogin(HashMap<String, String> token);

    int updateByPrimaryKeySelective(UserTable record);

    int updateByPrimaryKey(UserTable record);
    
    
    ArrayList<UserTable> loadUsersByToId(int tid);
    
    /**
     * 更新用户信息
     * @param userTable
     * @return
     */
    Integer updateAccount(UserTable userTable);
}