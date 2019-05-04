package com.score.dao;


import com.score.bean.User1;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface User1Mapper {

    void addUser(User1 sysUser);

    int updateUserByName(User1 sysUser);

    int selectByName(String name);
}