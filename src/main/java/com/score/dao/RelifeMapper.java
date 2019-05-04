package com.score.dao;

import com.score.bean.Relife;

import java.util.HashMap;
import java.util.List;

public interface RelifeMapper {
   List<Relife> selectAll(Relife record);

   Integer updateFlag(Relife relife);

   int insert(Relife record);
}
