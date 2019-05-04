package com.score.dao;

import com.score.bean.Staff;
import java.util.List;

public interface StaffMapper {
    List<Staff> selectAll(Staff record);

    Staff selectByPrimaryKey(Integer staffNo);

    int insert(Staff record);

    int deleteByPrimaryKey(Integer staffNo);

    int updateByPrimaryKey(Staff record);

}
