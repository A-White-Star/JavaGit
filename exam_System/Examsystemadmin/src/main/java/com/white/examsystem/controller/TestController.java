package com.white.examsystem.controller;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.model.Test;
import com.white.examsystem.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/houtai")
public class TestController {
    @Autowired
    TestService testService;
    @PostMapping("test")
    public RespBean add(@RequestBody Test item){ return testService.add(item);}

    @PutMapping("test")
    public RespBean update(@RequestBody Test item){
        return testService.update(item);
    }
    @PostMapping("enroll")
    public RespBean enrollTest(@RequestParam("testId") Integer testId, @RequestParam("userId") Integer userId){
        return testService.enrollTest(testId, userId)>0?RespBean.success(): RespBean.fail();
    }
    @DeleteMapping("test")
    public RespBean deleteByIds(@RequestBody  Long[] ids){
        if(ids == null){
            return RespBean.fail(-6,"缺少关键参数");
        }
        return testService.deleteByIds(ids);
    }
    @DeleteMapping("test/{id}")
    public RespBean deleteById(@PathVariable("id") Long id){
        return testService.deleteById(id);
    }
    @GetMapping("testbykey/{key}")
    public RespBean getListByKey(@PathVariable("key") String key){
        return testService.getListByKey(key);
    }
    @GetMapping("testbyid/{id}")
    public RespBean getById(@PathVariable("id") Long id){
        return testService.getById(id);
    }
    @GetMapping("test")
    public RespBean getAll(){
        return testService.getAll();
    }
    @GetMapping("scorelistbyuserid/{userId}")
    public RespBean getScoreListByUserId(@PathVariable("userId") Integer userId){
        return testService.getScoreListByUserId(userId);
    }
}
