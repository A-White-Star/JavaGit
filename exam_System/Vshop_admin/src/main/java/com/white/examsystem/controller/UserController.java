package com.white.examsystem.controller;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.model.User;
import com.white.examsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/config")
public class UserController {
    @Autowired
    UserService userService;
    //查找
    @GetMapping("/user")
    public RespBean getList(){
        return RespBean.success(userService.getAll());
    }
    @GetMapping("/user/{key}")
    public RespBean getListByKey(@PathVariable("key") String key){
       // if(key==null)return RespBean.success(yuanGongService.getAll());
        return RespBean.success(userService.getListByKey(key));
    }
    @GetMapping("/userid/{id}")
    public RespBean getListById(@PathVariable("id") Integer id){
        return RespBean.success(userService.getById(id));
    }
    //增删改查、初始化密码
    @PutMapping("/initpassword/{id}")
    public RespBean initPwd(@PathVariable("id") Integer id){
        return userService.initPassword(id);
    }
    @PutMapping("/user")

    public RespBean updateUser(@RequestBody User item){
        return userService.updateUser(item);
    }
    @DeleteMapping("/user/{id}")
    public RespBean deleteById(@PathVariable("id") Integer id){
        return userService.deleteUserById(id);
    }
    @DeleteMapping("/user")
    public RespBean deleteByIds(@RequestBody Integer[] ids){
        return userService.deleteUserByIds(ids);
    }
    @PostMapping("/user")
    public RespBean addUser(@RequestBody User item){
        return userService.addUser(item);
    }
    @GetMapping("/usercharacter/{id}")
    public RespBean getUserCharacterByUserId(@PathVariable("id") Integer id){
            if(id == null)return RespBean.fail(-6,"缺少关键参数");
            return RespBean.success(userService.getUserCharacterById(id));
    }
    @PostMapping("/usercharacter")
    public RespBean fenPeiJueSe(@RequestParam("userId") Integer userId, @RequestParam("characterIds") Integer[] characterIds){
        if(userId==null)return RespBean.fail(-6,"缺少关键参数");
        int delCount= userService.deleteUserCharacterByUserId(userId);
        int addCount=0;
        if(characterIds!=null&& characterIds.length>0) addCount= userService.addUserCharacterByUserId(userId, characterIds);
        return delCount+addCount>0?RespBean.success(): RespBean.fail();
    }


}
