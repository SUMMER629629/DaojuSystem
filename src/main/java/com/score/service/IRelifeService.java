package com.score.service;

import com.github.pagehelper.PageInfo;
import com.score.bean.Relife;

import java.util.HashMap;

public interface IRelifeService {

   PageInfo<HashMap> getAlltest(Relife relife, int page, int size);
}
