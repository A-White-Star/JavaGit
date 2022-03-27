package com.white.examsystem.dao;

import com.white.examsystem.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao {
    //search
    List<User> getUserAll();
    List<User> getUserListByKey(String key);
    User getUserById(Long id);
    //add
    int addUser(User item);
    //update
    int updateUserName(User item);
    int updatePassword(User item);
    //delete
    int deleteUserById(Long id);
    int deleteUserByIds(Long[] ids);

    int existsUserName(String userName);
//    int JinYong(Long id);
//    int QiYong(Long id);
}