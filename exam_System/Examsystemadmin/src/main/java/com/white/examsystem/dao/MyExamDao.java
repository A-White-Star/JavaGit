package com.white.examsystem.dao;

import com.white.examsystem.model.MyExam;
import com.white.examsystem.model.Test;
import com.white.examsystem.model.User;

import java.util.List;

public interface MyExamDao {
    List<MyExam> getAllScoreByUserId(Integer userId);  //根据当前用户登陆的账号获得所有考试成绩

//    List<User> userList(Integer testId); //所有报名该考试的名单
    List<Test> getTestListByUserId(Integer userId); //个人报名的所有考试名单
}
