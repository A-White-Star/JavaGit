package com.white.examsystem.dao;

import com.white.examsystem.model.Test;

import java.util.List;

public interface TestDao {
    List<Test> getAll(); //返回所有考试列表
    List<Test> getListByKey(String key); //查找考试
    Test getById(Long id); //通过id找考试
    Test getDetailsById(Long id); //返回考试信息

    int add(Test item); //创建考试
    int update(Test item); //更新考试信息
    int deleteById(Long id); //删除考试
    int deleteByIds(Long[] ids);

    List<Test> getShowList();
}
