package com.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.score.bean.ResultObject;
import com.score.bean.Daoju;
import com.score.service.IDaojuService;
import com.score.util.Constant;

@RequestMapping("/daoju")

@RestController //标识为返回类型为Json的控制
public class DaojuController {
    @Autowired
    private IDaojuService daojuService;
    //标识请求地址
    @RequestMapping("/getAllDaoju")
    public ResultObject<List<Daoju>> getDaoju(Daoju daoju,@RequestParam("limit") int limit,@RequestParam("page") int page) {
        PageInfo<Daoju> pageInfo=daojuService.getAll(daoju, page, limit);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!"+daoju);
        ResultObject<List<Daoju>> rs=new ResultObject<List<Daoju>>();
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getList());
        rs.setCount(pageInfo.getTotal());
        return rs;
    }

    @RequestMapping("/deleteDaoju")
    public ResultObject<Object> deleteDaoju(@RequestParam("daojuNo") int daojuNo) {
        Integer total=daojuService.deleteDaoju(daojuNo);
        //统一返回
        ResultObject<Object> rs=new ResultObject<Object>();
        if(null==total||0==total) {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("删除刀具信息失败");
        }else {
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("删除刀具信息成功");
        }
        return rs;
    }

    @RequestMapping("/addDaoju")
    public ResultObject<Object> addDaoju(Daoju daoju) {

        Integer daojuNo=daoju.getDaojuNo();
//        daoju.setStuPass(daojuNo.toString());
        Daoju result=daojuService.selectByNo(daojuNo);
        //统一返回
        ResultObject<Object> rs=new ResultObject<Object>();
        if(null==result) {
            daojuService.addDaoju(daoju);
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("增加刀具信息成功");
        }else {
            rs.setCode(Constant.HASE_RETUEN_CODE);
            rs.setMsg("刀具已存在");
        }
        return rs;
    }

    @RequestMapping("/updateDaoju")
    public ResultObject<Object> updateDaoju(Daoju daoju) {
        Integer total=daojuService.updateDaoju(daoju);
        //统一返回
        ResultObject<Object> rs=new ResultObject<Object>();
        if(null==total||0==total) {
            rs.setCode(Constant.FAILURE_RETUEN_CODE);
            rs.setMsg("修改刀具信息失败");
        }else {
            rs.setCode(Constant.SUCCESS_RETUEN_CODE);
            rs.setMsg("修改刀具信息成功");
        }
        return rs;
    }
}

