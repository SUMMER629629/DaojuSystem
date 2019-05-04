package com.score.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.score.bean.Relife;
import com.score.bean.Staff;
import com.score.dao.RelifeMapper;
import com.score.dao.StaffMapper;
import com.score.service.IRelifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("Relife")
@Transactional(readOnly = true)
public class RelifeServiceImpl implements IRelifeService {

   @Autowired
   private RelifeMapper relifeMapper;

   @Autowired
   private StaffMapper staffMapper;

   @Override
   public PageInfo<HashMap> getAlltest(Relife relife, int page, int size){
       PageHelper.startPage(page,size);
       List<Relife> list = relifeMapper.selectAll(relife);
       List<HashMap> listMaps = new ArrayList<HashMap>();
       for(Relife test : list){
           HashMap map = new HashMap();
           map.put("redaojuNo",test.getRedaojuNo());
           map.put("redaojuName",test.getRedaojuName());
           map.put("machineId","机床"+test.getMachineId());
           int state0 = test.getRedaojuState();
           String state = "";
           if(state0 == 1){
               state = "使用中";
           }
           else{
               state = "已弃用";
           }
           map.put("redaojuState",state);
           int staffid = test.getRestaffId();
           Staff staff = staffMapper.selectByPrimaryKey(staffid);
           map.put("restaffId",staff.getStaffName());
           Date durtime = test.getDurTime();
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//           System.out.println("!!!!!!!!!!!!!!!"+simpleDateFormat.format(durtime));
//           test.setDurTime(simpleDateFormat.format(durtime));
           map.put("durTime",simpleDateFormat.format(durtime));
           map.put("useTime",test.getUseTime());
           map.put("restTime",test.getRestTime());
           listMaps.add(map);
       }
       PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(listMaps);
       return pageInfo;
   }
}
