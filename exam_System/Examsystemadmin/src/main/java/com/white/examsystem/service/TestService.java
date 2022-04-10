package com.white.examsystem.service;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.dao.TestDao;
import com.white.examsystem.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    TestDao testDao;

    public RespBean add(Test item) {
        if(testDao.existsTest(item.getDate(),item.getPlace())==1){
            return RespBean.fail(-5,"已有考试预定！");
        }
        if(testDao.existsTeacher(item.getDate(),item.getTeacherName())==1){
            return RespBean.fail(-5,"该教师当前时间段已有任务");
        }
        int res = testDao.add(item);
        return res > 0 ? RespBean.success(item) : RespBean.fail();
    }

    public RespBean update(Test item) {
        if(testDao.existsTest(item.getDate(),item.getPlace())==1){
            return RespBean.fail(-5,"已有考试预定！");
        }
        if(testDao.existsTeacher(item.getDate(),item.getTeacherName())==1){
            return RespBean.fail(-5,"该教师当前时间段已有任务");
        }
        return testDao.update(item) > 0 ? RespBean.success() : RespBean.fail();
    }

    public int  enrollTest(Integer testId, Integer userId){
        return  testDao.enroll(testId, userId);
    }
    public RespBean deleteById(Long id) {
        return testDao.deleteById(id) > 0 ? RespBean.success() : RespBean.fail();
    }
    public RespBean deleteByIds(Long[] ids){ return testDao.deleteByIds(ids) > 0 ? RespBean.success() : RespBean.fail();}

    public RespBean getAll() {
        return RespBean.success(testDao.getAll());
    }

    public RespBean getListByKey(String key) {
        return RespBean.success(testDao.getListByKey(key));
    }

    public RespBean getById(Long id) {
        return RespBean.success(testDao.getById(id));
    }

    public RespBean getScoreListByUserId(Integer userId) {
        return RespBean.success(testDao.getScoreListByUserId(userId));
    }

    public List<Test> getShowList() {
        List<Test> showList = testDao.getShowList();
        return showList;
    }

}