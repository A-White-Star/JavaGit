package com.white.examsystem.dao;

import com.white.examsystem.model.ShangPin;

import java.util.List;

public interface ShangPinDao {
    List<ShangPin> getAll();
    List<ShangPin> getListByKey(String key);
    ShangPin getById(Long id);
    ShangPin getDetailsById(Long id);

    int add(ShangPin item);
    int update(ShangPin item);
    int deleteById(Long id);
    int deleteByIds(Long[] ids);

    List<ShangPin> getShowList();
}
