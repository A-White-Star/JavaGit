package com.white.examsystem.dao;

import com.white.examsystem.model.Character;
import com.white.examsystem.model.YuanGong;

import java.util.List;

public interface YuanGongDao {
    YuanGong loadUserByUsername(String username);

    List<Character> getJueSeList(Integer ygId);

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
