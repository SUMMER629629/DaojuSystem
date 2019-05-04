package com.score.dao;

import com.score.bean.Daoju;


import java.util.List;

public interface DaojuMapper {
    List<Daoju> selectAll(Daoju record);

    int deleteByPrimaryKey(Integer daojuNo);

    Daoju selectByPrimaryKey(Integer daojuNo);

    int insert(Daoju record);

    int updateByPrimaryKey(Daoju record);
}
