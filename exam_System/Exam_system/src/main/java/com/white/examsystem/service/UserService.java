package com.white.examadmin.service;
//
//import com.white.examadmin.common.RespBean;
//import com.white.examadmin.dao.UserDao;
//import com.white.examadmin.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//   // PasswordEncoder passwordEncoder;
//    public List<User> getUserAll() { return userDao.getUserAll(); }
//    public List<User> getUserListByKey(String key) { return userDao.getUserListByKey(key); }
//    public User getUserById(Long id) { return  userDao.getUserById(id);}
//
//    //注册新用户
//    public RespBean addUser(User item) {
//        if(item.getUserName()==null
//                ||item.getUserName().trim().length()==0
//                ||item.getPassword()==null) {return RespBean.fail(-6,"缺少关键参数");}
//        if(userDao.existsUserName(item.getUserName())==1){
//            return RespBean.fail(-5,"关键参数重复");
//        }
////        item.setPassword(passwordEncoder.encode(item.getMiMa()));
//        return userDao.addUser(item)==1?RespBean.success():RespBean.fail();
//    }
//
//    public RespBean updatePassword(Long id, String oldPassword,String newPassword) {
//        User old=userDao.getUserById(id);
//        if(old==null) return RespBean.fail(-7,"无此用户");
////        if(!passwordEncoder.matches(oldMiMa,old.getMiMa())){
////            return RespBean.fail(-8,"旧密码错误");
////        }
////        old.setPassword(passwordEncoder.encode(newMiMa));
//        old.setPassword(newPassword);
//        return userDao.updatePassword(old)==1?RespBean.success():RespBean.fail();
//    }
//
//    public RespBean updateUserName(Long id, String oldName,String newName) {
//        User old=userDao.getUserById(id);
//        if(old==null) return RespBean.fail(-7,"无此用户");
//        old.setUserName(newName);
//        return userDao.updateUserName(old)==1?RespBean.success():RespBean.fail();
//    }
//
//    public RespBean deleteUserById(Long id){
//        return userDao.deleteUserById(id)==1?RespBean.success():RespBean.fail();
//    }
//    public RespBean deleteUserByIds(Long[] ids) {
//        return userDao.deleteUserByIds(ids)>0?RespBean.success():RespBean.fail();
//    }
//    public int existsUserName(String  userName) {
//        return userDao.existsUserName(userName);
//    }
////    public RespBean JinYong(Long id) {
////        return userDao.JinYong(id)>0?RespBean.success():RespBean.fail();
////    }
////    public RespBean QiYong(Long id) {
////        return userDao.QiYong(id)>0?RespBean.success():RespBean.fail();
////    }
//}
