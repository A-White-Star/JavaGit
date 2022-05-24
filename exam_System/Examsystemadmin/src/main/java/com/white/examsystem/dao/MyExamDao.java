package com.white.examsystem.dao;

import com.white.examsystem.model.MyExam;
import com.white.examsystem.model.Test;
import com.white.examsystem.model.User;

import java.util.List;

public interface MyExamDao {
    List<String> getStudentByTestId(Integer testId);
}
