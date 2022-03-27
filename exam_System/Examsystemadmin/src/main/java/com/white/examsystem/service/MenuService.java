package com.white.examsystem.service;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.dao.MenuDao;
import com.white.examsystem.model.Menu;
import com.white.examsystem.model.YuanGong;
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
    public List<Menu> getSub(){ return menuDao.getSubMenu(); }

    public List<Menu> getMenuListUserId(){
        Integer id =((YuanGong)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return menuDao.getMenuListByUserId(id);
    }

    public List<Menu> getList(){
        return menuDao.getAll();
    }

//    public List<Menu> getListByKey(String key){
//        return menuDao.getListByKey(key);
//    }
//
//    public Menu getById(Integer id){
//        return menuDao.getById(id);
//    }
//
//    public RespBean addCaiDan(Menu item){
//        if(menuDao.existsMingCheng(item.getMingCheng(), item.getFuCaiDanId())>0){
//            return RespBean.fail(-5,"菜单名称重复！");
//        }else if(menuDao.addCaiDan(item)>0){
//            return RespBean.success();
//        }else {
//            return RespBean.fail();
//        }
//    }
//
//    public RespBean updateCaiDan(Menu item){
//        Menu old= dao.getById(item.getId());
//        if(!old.getMingCheng().equals(item.getMingCheng())&&dao.existsMingCheng(item.getMingCheng(), item.getFuCaiDanId())>0){
//            return RespBean.fail(-5,"菜单名称重复！");
//        }else if(dao.updateCaiDan(item)>0){
//            return RespBean.success();
//        }else {
//            return RespBean.fail();
//        }
//    }
//
//    public RespBean deleteById(Integer id){
//       if(dao.deleteCaiDanById(id)>0){
//            return RespBean.success();
//        }else {
//            return RespBean.fail();
//        }
//    }
//
//    public RespBean deleteByIds(Integer[] ids){
//        if(dao.deleteCaiDanByIds(ids)>0){
//            return RespBean.success();
//        }else {
//            return RespBean.fail();
//        }
//    }

    public List<Menu> getAllMenuAndSubMenu(){
        return menuDao.getAllMenuAndSubMenuList();
    }

    public List<Menu> getMenuByCharacterId(Integer characterId){
            return menuDao.getMenuListByCharacterId(characterId);
    }
}
