package com.example.vshopadmin.controller;

import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.dao.ShangPinTuPianDao;
import com.example.vshopadmin.model.ShangPin;
import com.example.vshopadmin.service.ShangPinService;
import com.example.vshopadmin.service.ShangPinTuPianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
