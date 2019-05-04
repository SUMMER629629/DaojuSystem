package com.score.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.score.bean.Daoju;

public interface IDaojuService {
    /**
     *全量查询用户
     * @return
     */
    PageInfo<Daoju> getAll(Daoju daoju, int page, int size);

    Integer deleteDaoju(int daojuNo);

    Daoju selectByNo(int daojuNo);

    Integer addDaoju(Daoju daoju);

    Integer updateDaoju(Daoju daoju);
}
