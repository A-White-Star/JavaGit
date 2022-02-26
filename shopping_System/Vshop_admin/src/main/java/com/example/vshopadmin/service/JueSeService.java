package com.example.vshopadmin.service;

import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.dao.JueSeDao;
import com.example.vshopadmin.model.JueSe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JueSeService {
    @Autowired
    JueSeDao dao;
    public List<JueSe> getAllJueSeList(){
        return  dao.getAllJueSeList();
    }

    public JueSe getJueSeById(Integer id){
        return dao.getJueSeById(id);
    }
    public RespBean saveJueSe(JueSe item){
        if(dao.existsJueSeMingCheng(item.getJueSeMingCheng())==1){
            return RespBean.fail(-5,"角色名称重复！");
        }else if(dao.addJueSe(item)==1){
            return RespBean.success();
        }else{
            return RespBean.fail();
        }
    }

    public RespBean updateJueSe(JueSe item){
       return dao.updateJueSe(item)==1?RespBean.success():RespBean.fail();
    }

    public RespBean deleteJueSeById(Integer id){
        return dao.deleteJueSeById(id) == 1?RespBean.success():RespBean.fail();
    }

    public RespBean deleteJueSeByIds(Integer[] ids) {
        return dao.deleteJueSeByIds(ids) == 1?RespBean.success():RespBean.fail();
    }
    public RespBean updateJueSeCaiDan(Integer jueSeId, Integer[] caiDanIds){
        dao.deleteJueSeCaiDanByJueSeId(jueSeId);
        dao.addJueSeCaiDanByJueSeId(jueSeId, caiDanIds);
        return RespBean.success();
    }
}
