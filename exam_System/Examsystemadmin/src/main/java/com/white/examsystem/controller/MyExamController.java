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
    @GetMapping("scorebyuserid/{userId}")
    public RespBean getAllScoreByUserId(@PathVariable("userId") Integer userId){
        return myExamService.getAllScoreByUserId(userId);
    }
    @GetMapping("testbyuserid/{userId}")
    public RespBean getTestListByUserId(@PathVariable("userId") Integer userId){
        return myExamService.getTestListByUserId(userId);
    }
}
