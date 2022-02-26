package com.example.vshopadmin.dao;

import com.example.vshopadmin.model.JueSe;

import java.util.List;

public interface JueSeDao {
    List<JueSe> getAllJueSeList();

    JueSe getJueSeById(Integer id);
    int addJueSe(JueSe item);
    int updateJueSe(JueSe item);
    int existsJueSeMingCheng(String jueSeMingCheng);
    int deleteJueSeById(Integer id);

    int deleteJueSeByIds(Integer[] ids);

    int deleteJueSeCaiDanByJueSeId(Integer jueSeId);
    int addJueSeCaiDanByJueSeId(Integer jueSeId,Integer[] caiDanIds);

}
