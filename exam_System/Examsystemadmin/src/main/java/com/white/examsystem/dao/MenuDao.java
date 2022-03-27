package com.white.examsystem.dao;

import com.white.examsystem.model.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> getMenuList();
    List<Menu> getMenuListByUserId(Integer userId);

    List<Menu> getAll();
    List<Menu> getListByKey(String key);
    List<Menu> getSubMenu();
    List<Menu> getAllMenuAndSubMenuList();
    List<Menu> getMenuListByCharacterId(Integer CharacterId);
    Menu getById(Integer id);

    int addMenu(Menu item);
    int updateMenu(Menu item);
    int deleteMenuById(Integer id);
    int deleteMenuByIds(Integer[] ids);

    int existsMenuName(String  mingCheng, Integer fuCaiDanId);
}
