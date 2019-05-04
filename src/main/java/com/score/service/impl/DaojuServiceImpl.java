package com.score.service.impl;

import com.score.bean.Relife;
import com.score.dao.RelifeMapper;
import com.score.service.IDaojuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.score.bean.Daoju;
import com.score.dao.DaojuMapper;
import com.score.service.IDaojuService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("Daoju")
public class DaojuServiceImpl implements IDaojuService {
    @Autowired
    private DaojuMapper daojuMapper;

    @Autowired
    private RelifeMapper relifeMapper;

    @Override
    public PageInfo<Daoju> getAll(Daoju daoju, int page, int size) {
        // 首先开启PageHelper的分页
        PageHelper.startPage(page, size);
        // 查询分页信息 调用方式与普通方式一致
        List<Daoju> list = daojuMapper.selectAll(daoju);
        PageInfo<Daoju> pageInfo=new PageInfo<Daoju>(list);
        return pageInfo;
    }

    @Override
    public Integer deleteDaoju(int daojuNo) {
        Integer total=daojuMapper.deleteByPrimaryKey(daojuNo);
        return total;
    }

    /**
     *查询是否存在
     * @return
     */
    @Override
    public Daoju selectByNo(int daojuNo) {
        Daoju daoju=daojuMapper.selectByPrimaryKey(daojuNo);
        return daoju;
    }

    //增加刀具信息
    @Override
    public Integer addDaoju(Daoju daoju) {
        Relife relife = new Relife();
        relife.setRedaojuNo(daoju.getDaojuNo());
        relife.setRedaojuName(daoju.getDaojuName());
        relife.setMachineId(1);
        relife.setRestaffId(1);
        relife.setDurTime(new Date());
        relife.setRedaojuState(1);
        relife.setUseTime(Float.valueOf(0));
        relife.setRestTime(Float.valueOf(180));
        relife.setFlag(1);
        Integer total=daojuMapper.insert(daoju);
        Integer total1=relifeMapper.insert(relife);
        return total;
    }

    //修改
    @Override
    public Integer updateDaoju(Daoju daoju) {
        Integer total=daojuMapper.updateByPrimaryKey(daoju);
        return total;
    }
}
