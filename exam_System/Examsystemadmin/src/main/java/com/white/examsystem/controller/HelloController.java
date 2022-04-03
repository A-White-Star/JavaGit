package com.white.examsystem.controller;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.service.ShangPinService;
import com.white.examsystem.service.ShangPinTuPianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class HelloController {
    @Autowired
    ShangPinService service;
    @Autowired
    ShangPinTuPianService tuPianService;

    @GetMapping("getall")
   public RespBean getShangPinList(){
        return RespBean.success(service.getShowList());
    }
    @GetMapping("getdetail/{id}")
    public RespBean getShangPinDetailById(@PathVariable("id") Long id){
        return  RespBean.success(service.getById(id));
    }
    @GetMapping("getdetailimage/{id}")
    public RespBean getShangPinDetailImagesByShangPinId(@PathVariable("id") Long shangPinId){
        return  RespBean.success(tuPianService.getShangPinTuPianListByShangPinId(shangPinId));
    }
}