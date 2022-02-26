package com.example.vshopadmin.dao;

import com.example.vshopadmin.model.YongHu;
import com.example.vshopadmin.model.YuanGong;

import java.util.List;

public interface YongHuDao {
    List<YongHu> getAll();
    List<YongHu> getListByKey(String key);
    YongHu getById(Long id);

    int addYongHu(YongHu item);
    int updateYongHu(YongHu item);
    int deleteYongHuById(Long id);
    int deleteYongHuByIds(Long[] ids);
    int existsYongHuMing(String  yongHuMing);
    int JinYong(Long id);
    int QiYong(Long id);

}
