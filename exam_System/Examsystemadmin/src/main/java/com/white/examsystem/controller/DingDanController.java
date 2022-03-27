package com.white.examsystem.controller;

import com.white.examsystem.common.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("houtai")
public class DingDanController {
    @RequestMapping("jiance")
    public RespBean jiance(){
        return RespBean.success();
    }
}
