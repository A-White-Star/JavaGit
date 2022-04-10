package com.white.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExam {
    Integer id;
    Integer userId;
    Integer testId;
    Integer score;

//    private List<User> userList; //所有报名该考试的名单
    private List<Test> testList; //个人报名的所有考试名单
}
