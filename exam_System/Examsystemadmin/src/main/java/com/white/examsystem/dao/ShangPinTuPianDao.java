package com.white.examsystem.dao;

import com.white.examsystem.model.ShangPinTuPian;

import java.util.List;

public interface ShangPinTuPianDao {
    int add(ShangPinTuPian item);
    int deleteById(Long id);
    int deleteByIds(Long[] ids);
    int deleteByShangPinId(Long shangPinId);

    List<ShangPinTuPian> getTuPianListByShangPinId(Long shangPinId);
}
