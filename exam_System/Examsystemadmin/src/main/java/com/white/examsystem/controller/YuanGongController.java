package com.white.examsystem.controller;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.model.YuanGong;
import com.white.examsystem.service.YuanGongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/config")
public class YuanGongController {
    @Autowired
    YuanGongService yuanGongService;
    //查找
    @GetMapping("/yuangong")
    public RespBean getList(){
        return RespBean.success(yuanGongService.getAll());
    }
    @GetMapping("/yuangong/{key}")
    public RespBean getListByKey(@PathVariable("key") String key){
       // if(key==null)return RespBean.success(yuanGongService.getAll());
        return RespBean.success(yuanGongService.getListByKey(key));
    }
    @GetMapping("/yuangongid/{id}")
    public RespBean getListById(@PathVariable("id") Integer id){
        return RespBean.success(yuanGongService.getById(id));
    }
    //增删改查、初始化密码
    @PutMapping("/initpassword/{id}")
    public RespBean initPwd(@PathVariable("id") Integer id){
        return yuanGongService.initPassword(id);
    }
    @PutMapping("/yuangong")

    public RespBean updateYuanGong(@RequestBody YuanGong item){
        return yuanGongService.updateYuanGong(item);
    }
    @DeleteMapping("/yuangong/{id}")
    public RespBean deleteById(@PathVariable("id") Integer id){
        return yuanGongService.deleteYuanGongById(id);
    }
    @DeleteMapping("/yuangong")
    public RespBean deleteByIds(@RequestBody Integer[] ids){
        return yuanGongService.deleteYuanGongByIds(ids);
    }
    @PostMapping("/yuangong")
    public RespBean addYuanGong(@RequestBody YuanGong item){
        return yuanGongService.addYuanGong(item);
    }
    @GetMapping("/yuangongjuese/{id}")
    public RespBean getYuanGongJueSeByYuanGongId(@PathVariable("id") Integer id){
            if(id == null)return RespBean.fail(-6,"缺少关键参数");
            return RespBean.success(yuanGongService.getYuanGongJueSeById(id));
    }
    @PostMapping("/yuangongjuese")
    public RespBean fenPeiJueSe(@RequestParam("yuanGongId") Integer yuanGongId, @RequestParam("jueSeIds") Integer[] jueSeIds){
        if(yuanGongId==null)return RespBean.fail(-6,"缺少关键参数");
        int delCount=yuanGongService.deleteYuanGongJueSeByYuanGongId(yuanGongId);
        int addCount=0;
        if(jueSeIds!=null&& jueSeIds.length>0) addCount=yuanGongService.addYuanGongJueSeByYuanGongId(yuanGongId, jueSeIds);
        return delCount+addCount>0?RespBean.success(): RespBean.fail();
    }


}
