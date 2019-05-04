package com.score.dao;

import com.score.bean.Plc;

public interface PredictMapper {
    void addUser(Plc sysUser);

    int updateUserById(Plc sysUser);

    int selectById(Integer id);

}
