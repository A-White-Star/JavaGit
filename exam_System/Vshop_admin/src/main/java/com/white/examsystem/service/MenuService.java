package com.white.examsystem.service;

import com.white.examsystem.dao.MenuDao;
import com.white.examsystem.model.Menu;
import com.white.examsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;

    public List<Menu> getMenuList() {
        return menuDao.getMenuList();
    }
    public List<Menu> getFirstMenu(){ return menuDao.getFirstMenu(); }

    public List<Menu> getMenuListByUserId(){
        Integer id =((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return menuDao.getMenuListByUserId(id);
    }

    public List<Menu> getList(){
        return menuDao.getAll();
    }

    public List<Menu> getListByKey(String key){
        return menuDao.getListByKey(key);
    }

    public Menu getById(Integer id){
        return menuDao.getById(id);
    }

    public List<Menu> getAllMenuAndSubMenuList(){
        return menuDao.getAllMenuAndSubMenuList();
    }

    public List<Menu> getMenuListCharacterId(Integer characterId){
            return menuDao.getMenuListCharacterId(characterId);
    }
}
