package com.white.examsystem.controller;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.model.User;
import com.white.examsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/background")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public RespBean getAll(){
        return RespBean.success(userService.getUserAll());
    }

    @GetMapping("/user/{key}")
    public RespBean geUsertListByKey(@PathVariable("key") String key){
        return RespBean.success(userService.getUserListByKey(key));
    }

    @GetMapping("/user/{id}")
    public RespBean getYongHuById(@PathVariable("id") Long id){
        return RespBean.success(userService.getUserById(id));
    }

    @PostMapping("/user")
    public RespBean add(@RequestBody User item){return userService.addUser(item);}

//    @PostMapping("/user/tingyong/{id}")
//    public RespBean tingYong(@PathVariable("id") Long id){
//        return yongHuService.JinYong(id);
//    }
//
//    @PostMapping("/user/qiyong/{id}")
//    public RespBean qiYong(@PathVariable("id") Long id){
//        return yongHuService.QiYong(id);
//    }
}