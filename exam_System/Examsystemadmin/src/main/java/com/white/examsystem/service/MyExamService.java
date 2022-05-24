package com.white.examsystem.service;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.dao.MyExamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyExamService {
    @Autowired
    MyExamDao myExamDao;

    public RespBean getStudentByTestId(Integer testId){
        return RespBean.success(myExamDao.getStudentByTestId(testId));
    }

}
