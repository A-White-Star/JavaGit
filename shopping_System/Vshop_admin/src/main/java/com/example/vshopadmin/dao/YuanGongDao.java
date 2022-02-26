package com.example.vshopadmin.dao;

import com.example.vshopadmin.model.CaiDan;
import com.example.vshopadmin.model.JueSe;
import com.example.vshopadmin.model.YuanGong;

import java.util.List;

public interface YuanGongDao {
    YuanGong loadUserByUsername(String username);

    List<JueSe> getJueSeList(Integer ygId);

    List<YuanGong> getAll();
    List<YuanGong> getListByKey(String key);
    YuanGong getById(Integer id);

    int addYuanGong(YuanGong item);
    int updateYuanGong(YuanGong item);
    int deleteYuanGongById(Integer id);
    int deleteYuanGongByIds(Integer[] ids);

    int initPassword(Integer id);

    int existsZhenShiXingMing(String  zhenShiXingMing);
    int existsYongHuMing(String  yongHuMing);
    int existsShouJiHao(String  shouJiHao);

    int deleteYuanGongJueSeByYuanGongId(Integer yuanGongId);

    int addYuanGongJueSeByYuanGongId(Integer yuanGongId, Integer[] jueSeIds);
}
