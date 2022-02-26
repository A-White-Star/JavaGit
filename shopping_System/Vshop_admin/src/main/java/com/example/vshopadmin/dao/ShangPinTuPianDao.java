package com.example.vshopadmin.dao;

import com.example.vshopadmin.model.ShangPinTuPian;

import java.util.List;

public interface ShangPinTuPianDao {
    int add(ShangPinTuPian item);
    int deleteById(Long id);
    int deleteByIds(Long[] ids);
    int deleteByShangPinId(Long shangPinId);

    List<ShangPinTuPian> getTuPianListByShangPinId(Long shangPinId);
}
