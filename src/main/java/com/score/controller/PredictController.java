package com.score.controller;

//import com.score.bean.Relife;
//import com.score.bean.Relife;
import com.score.service.IPredictService;
//import com.score.service.IRelifeService;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/predict")
@RestController //标识为返回类型为Json的控制
public class PredictController {
    @Autowired
    private IPredictService predictService;


    @PostMapping("/import")
    public boolean addUser(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = predictService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }

//    @RequestMapping("/getAll")
//    public ResultObject<List<Relife>> getDaoju(Relife relife, @RequestParam("limit") int limit, @RequestParam("page") int page) {
//        PageInfo<Relife> pageInfo=relifeService.getAll(relife, page, limit);
//        ResultObject<List<Relife>> rs=new ResultObject<List<Relife>>();
//        rs.setCode(Constant.SUCCESS_RETUEN_CODE);
//        rs.setMsg("查询成功");
//        rs.setData(pageInfo.getList());
//        rs.setCount(pageInfo.getTotal());
//        return rs;
//    }


}
