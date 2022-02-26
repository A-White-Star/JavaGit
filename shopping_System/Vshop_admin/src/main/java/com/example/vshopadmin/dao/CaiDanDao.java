package com.example.vshopadmin.dao;

import com.example.vshopadmin.model.CaiDan;

import java.util.List;

public interface CaiDanDao {
    List<CaiDan> getCaiDanList();
    List<CaiDan> getCaiDanListByYuanGongId(Integer yuangongId);

    List<CaiDan> getAll();
    List<CaiDan> getListByKey(String key);
    List<CaiDan> getYiJi();
    List<CaiDan> getAllCaiDanAndZiCaiDanList();
    List<CaiDan> getCaiDanListByJueSeId(Integer jueSeId);
    CaiDan getById(Integer id);

    int addCaiDan(CaiDan item);
    int updateCaiDan(CaiDan item);
    int deleteCaiDanById(Integer id);
    int deleteCaiDanByIds(Integer[] ids);

    int existsMingCheng(String  mingCheng, Integer fuCaiDanId);
}
