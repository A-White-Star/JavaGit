package com.white.examsystem.dao;

import com.white.examsystem.model.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> getMenuList();//联合角色
    List<Menu> getMenuListByUserId(Integer userId);

    List<Menu> getAll();
    List<Menu> getListByKey(String key);
    List<Menu> getFirstMenu();
    List<Menu> getAllMenuAndSubMenuList();
    List<Menu> getMenuListCharacterId(Integer characterId);
    Menu getById(Integer id);


}
