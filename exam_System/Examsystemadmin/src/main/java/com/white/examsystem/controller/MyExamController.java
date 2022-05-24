package com.white.examsystem.controller;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.service.MyExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/houtai")
public class MyExamController {
    @Autowired
    MyExamService myExamService;
    @GetMapping("studentbytestid/{testId}")
    public RespBean getStudentByTestId(@PathVariable("testId") Integer testId){
        return myExamService.getStudentByTestId(testId);
    }
}
