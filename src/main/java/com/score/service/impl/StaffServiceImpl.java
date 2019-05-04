package com.score.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.score.bean.Staff;
import com.score.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.score.dao.StaffMapper;

import java.util.List;

@Service("Staff")
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private StaffMapper staffMapper;

    @Override
    public PageInfo<Staff> getAll(Staff staff, int page, int size){
        PageHelper.startPage(page,size);
        List<Staff> list = staffMapper.selectAll(staff);
        PageInfo<Staff> pageInfo = new PageInfo<Staff>(list);
        return pageInfo;
    }

    @Override
    public Staff selectByNo(int staffNo){
        Staff staff=staffMapper.selectByPrimaryKey(staffNo);
        return staff;
    }

    @Override
    public Integer addStaff(Staff staff){
        Integer total=staffMapper.insert(staff);
        return total;
    }

    @Override
    public Integer deleteStaff(int staffNo){
        Integer total=staffMapper.deleteByPrimaryKey(staffNo);
        return total;
    }

    @Override
    public Integer updateStaff(Staff staff){
        Integer total=staffMapper.updateByPrimaryKey(staff);
        return total;
    }
}
