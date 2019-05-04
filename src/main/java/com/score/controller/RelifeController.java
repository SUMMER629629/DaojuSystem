package com.score.controller;

import com.github.pagehelper.PageInfo;
import com.score.bean.Relife;
import com.score.bean.ResultObject;
import com.score.service.IRelifeService;
import com.score.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/relife")
@RestController //标识为返回类型为Json的控制
public class RelifeController {
    @Autowired
    private IRelifeService relifeService;

    @RequestMapping("/getAll")
    public ResultObject<List<HashMap>> getRelife(Relife relife, @RequestParam("limit") int limit, @RequestParam("page") int page) {
        PageInfo<HashMap> pageInfo=relifeService.getAlltest(relife, page, limit);
        ResultObject<List<HashMap>> rs=new ResultObject<List<HashMap>>();
        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
        rs.setMsg("查询成功");
        rs.setData(pageInfo.getList());
        rs.setCount(pageInfo.getTotal());
        return rs;
    }

}
