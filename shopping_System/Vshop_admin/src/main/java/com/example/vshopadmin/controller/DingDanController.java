package com.example.vshopadmin.controller;

import com.example.vshopadmin.common.RespBean;
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
