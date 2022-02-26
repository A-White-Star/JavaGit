package com.example.vshopadmin.service;

import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.config.SecrityConfig;
import com.example.vshopadmin.dao.CaiDanDao;
import com.example.vshopadmin.model.CaiDan;
import com.example.vshopadmin.model.YuanGong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaiDanService {
    @Autowired
    CaiDanDao dao;

    public List<CaiDan> getCaiDanList() {
        return dao.getCaiDanList();
    }
    public List<CaiDan> getYiJi(){ return dao.getYiJi(); }

    public List<CaiDan> getCaiDanListYuanGongId(){
        Integer id =((YuanGong)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return dao.getCaiDanListByYuanGongId(id);
    }

    public List<CaiDan> getList(){
        return dao.getAll();
    }

    public List<CaiDan> getListByKey(String key){
        return dao.getListByKey(key);
    }

    public CaiDan getById(Integer id){
        return dao.getById(id);
    }

    public RespBean addCaiDan(CaiDan item){
        if(dao.existsMingCheng(item.getMingCheng(), item.getFuCaiDanId())>0){
            return RespBean.fail(-5,"菜单名称重复！");
        }else if(dao.addCaiDan(item)>0){
            return RespBean.success();
        }else {
            return RespBean.fail();
        }
    }

    public RespBean updateCaiDan(CaiDan item){
        CaiDan old= dao.getById(item.getId());
        if(!old.getMingCheng().equals(item.getMingCheng())&&dao.existsMingCheng(item.getMingCheng(), item.getFuCaiDanId())>0){
            return RespBean.fail(-5,"菜单名称重复！");
        }else if(dao.updateCaiDan(item)>0){
            return RespBean.success();
        }else {
            return RespBean.fail();
        }
    }

    public RespBean deleteById(Integer id){
       if(dao.deleteCaiDanById(id)>0){
            return RespBean.success();
        }else {
            return RespBean.fail();
        }
    }

    public RespBean deleteByIds(Integer[] ids){
        if(dao.deleteCaiDanByIds(ids)>0){
            return RespBean.success();
        }else {
            return RespBean.fail();
        }
    }

    public List<CaiDan> getAllCaiDanAndZiCaiDan(){
        return dao.getAllCaiDanAndZiCaiDanList();
    }

    public List<CaiDan> getCaiDanByJueSeId(Integer jueSeId){
            return dao.getCaiDanListByJueSeId(jueSeId);
    }
}
