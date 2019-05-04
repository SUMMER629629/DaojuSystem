package com.score.controller;


import com.github.pagehelper.PageInfo;
import com.score.bean.Staff;
import com.score.bean.ResultObject;
import com.score.service.IStaffService;
import com.score.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/staff")
@RestController //标识为返回类型为Json的控制
public class StaffController {
    @Autowired
    private IStaffService staffService;
    //标识请求地址
    @RequestMapping("/getAllStaff")
    public ResultObject<List<Staff>> getDaoju(Staff staff, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        PageInfo<Staff> pageInfo=staffService.getAll(staff, page, limit);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!"+daoju);
        ResultObject<List<Staff>> rs=new ResultObject<List<Staff>>();
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getList());
        rs.setCount(pageInfo.getTotal());
        return rs;
    }

    @RequestMapping("/addStaff")
    public ResultObject<Object> addStaff(Staff staff) {

        Integer staffNo=staff.getStaffNo();
//        daoju.setStuPass(daojuNo.toString());
        Staff result=staffService.selectByNo(staffNo);
        //统一返回
        ResultObject<Object> rs=new ResultObject<Object>();
        if(null==result) {
            staffService.addStaff(staff);
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("增加员工信息成功");
        }else {
            rs.setCode(Constant.HASE_RETUEN_CODE);
            rs.setMsg("员工已存在");
        }
        return rs;
    }

    @RequestMapping("/deleteStaff")
    public ResultObject<Object> deleteStaff(@RequestParam("staffNo") int staffNo) {
        Integer total=staffService.deleteStaff(staffNo);
        //统一返回
        ResultObject<Object> rs=new ResultObject<Object>();
        if(null==total||0==total) {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("删除员工信息失败");
        }else {
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("删除员工信息成功");
        }
        return rs;
    }

    @RequestMapping("/updateStaff")
    public ResultObject<Object> updateStaff(Staff staff) {
        Integer total=staffService.updateStaff(staff);
        //统一返回
        ResultObject<Object> rs=new ResultObject<Object>();
        if(null==total||0==total) {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("修改员工信息失败");
        }else {
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("修改员工信息成功");
        }
        return rs;
    }
}
