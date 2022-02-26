package com.example.vshopadmin.controller;

import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.model.YongHu;
import com.example.vshopadmin.service.YongHuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/houtai")
public class YongHuController {
    @Autowired
    YongHuService yongHuService;

    @GetMapping("/yonghu")
    public RespBean getAll(){
        return RespBean.success(yongHuService.getAll());
    }

    @GetMapping("/yonghu/{key}")
    public RespBean getListByKey(@PathVariable("key") String key){
        return RespBean.success(yongHuService.getListByKey(key));
    }

    @GetMapping("/yonghu/{id}")
    public RespBean getYongHuById(@PathVariable("id") Long id){
        return RespBean.success(yongHuService.getById(id));
    }

    @PostMapping("/yonghu")
    public RespBean add(@RequestBody YongHu item){return yongHuService.addYongHu(item);}

    @PostMapping("/yonghu/tingyong/{id}")
    public RespBean tingYong(@PathVariable("id") Long id){
        return yongHuService.JinYong(id);
    }

    @PostMapping("/yonghu/qiyong/{id}")
    public RespBean qiYong(@PathVariable("id") Long id){
        return yongHuService.QiYong(id);
    }
}
