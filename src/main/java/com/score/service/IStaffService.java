package com.score.service;

import com.github.pagehelper.PageInfo;
import com.score.bean.Staff;

public interface IStaffService {
    PageInfo<Staff> getAll(Staff staff, int page, int size);

    Staff selectByNo(int staffNo);

    Integer addStaff(Staff staff);

    Integer deleteStaff(int staffNo);

    Integer updateStaff(Staff staff);
}
