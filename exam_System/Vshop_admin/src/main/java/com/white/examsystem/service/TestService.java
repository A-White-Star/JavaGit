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
        int res = testDao.add(item);
        return res > 0 ? RespBean.success(item) : RespBean.fail();
    }

    public RespBean update(Test item) {
        return testDao.update(item) > 0 ? RespBean.success() : RespBean.fail();
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

    public List<Test> getShowList() {
        List<Test> showList = testDao.getShowList();
        return showList;
    }

}