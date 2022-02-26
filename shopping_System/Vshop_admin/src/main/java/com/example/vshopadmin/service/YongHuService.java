package com.example.vshopadmin.service;

import com.example.vshopadmin.common.RespBean;
import com.example.vshopadmin.dao.CaiDanDao;
import com.example.vshopadmin.dao.YongHuDao;
import com.example.vshopadmin.model.CaiDan;
import com.example.vshopadmin.model.YongHu;
import com.example.vshopadmin.model.YuanGong;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YongHuService {
    @Autowired
    private YongHuDao dao;
    @Autowired
    PasswordEncoder passwordEncoder;
    public List<YongHu> getAll() { return dao.getAll(); }
    public List<YongHu> getListByKey(String key) { return dao.getListByKey(key); }
    public YongHu getById(Long id) { return  dao.getById(id);}

    public RespBean addYongHu(YongHu item) {
        if(item.getYongHuMing()==null
                ||item.getYongHuMing().trim().length()==0
                ||item.getMiMa()==null) {return RespBean.fail(-6,"缺少关键参数");}
        if(dao.existsYongHuMing(item.getYongHuMing())==1){
            return RespBean.fail(-5,"关键参数重复");
        }
        item.setMiMa(passwordEncoder.encode(item.getMiMa()));
        return dao.addYongHu(item)==1?RespBean.success():RespBean.fail();
    }

    public RespBean xiuGaiMiMa(Long id, String oldMiMa,String newMiMa) {
        YongHu old=dao.getById(id);
        if(old==null) return RespBean.fail(-7,"无此用户");
        if(!passwordEncoder.matches(oldMiMa,old.getMiMa())){
            return RespBean.fail(-8,"旧密码错误");
        }
        old.setMiMa(passwordEncoder.encode(newMiMa));
        return dao.updateYongHu(old)==1?RespBean.success():RespBean.fail();
    }
    public RespBean deleteYongHuById(Long id){
        return dao.deleteYongHuById(id)==1?RespBean.success():RespBean.fail();
    }
    public RespBean deleteYongHuByIds(Long[] ids) {
        return dao.deleteYongHuByIds(ids)>0?RespBean.success():RespBean.fail();
    }
    public int existsYongHuMing(String  yongHuMing) {
        return dao.existsYongHuMing(yongHuMing);
    }
    public RespBean JinYong(Long id) {
        return dao.JinYong(id)>0?RespBean.success():RespBean.fail();
    }
    public RespBean QiYong(Long id) {
        return dao.QiYong(id)>0?RespBean.success():RespBean.fail();
    }
}
