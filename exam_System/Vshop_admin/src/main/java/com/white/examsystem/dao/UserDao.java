package com.white.examsystem.dao;

import com.white.examsystem.model.Character;
import com.white.examsystem.model.User;

import java.util.List;

public interface UserDao {
    User loadUserByUsername(String username);

    List<Character> getCharacterList(Integer userId);

    List<User> getAll();
    List<User> getListByKey(String key);
    User getById(Integer id);

    int addUser(User item);
    int updateUser(User item);
    int deleteUserById(Integer id);
    int deleteUserByIds(Integer[] ids);

    int initPassword(Integer id);

    int existsUserName(String  userName);
    int existsPhone(String  phone);

    int deleteUserCharacterByUserId(Integer userId);

    int addUserCharacterByUserId(Integer userId, Integer[] characterIds);
}
