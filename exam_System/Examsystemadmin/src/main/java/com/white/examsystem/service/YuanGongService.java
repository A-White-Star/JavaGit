package com.white.examsystem.service;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.dao.YuanGongDao;
import com.white.examsystem.model.Character;
import com.white.examsystem.model.YuanGong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YuanGongService implements UserDetailsService {
    @Autowired
    private YuanGongDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        YuanGong yg=dao.loadUserByUsername(username);
        if(yg==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Character> characterList =dao.getJueSeList(yg.getId());
        yg.setCharacterList(characterList);
        return yg;
    }
    //拉取所有员工
    public List<YuanGong> getAll(){
        return dao.getAll();
    }
    public List<YuanGong> getListByKey(String key){
        return dao.getListByKey(key);
    }
    public YuanGong getById(Integer id){
        return dao.getById(id);
    }
    //增删改、批量删除
    public RespBean addYuanGong(YuanGong item){
        if(item==null
                ||item.getZhenShiXingMing()==null
                ||item.getYongHuMing()==null
                ||item.getShouJiHao()==null){
            return RespBean.fail(-6,"缺少关键参数");
        }
        if(dao.existsYongHuMing(item.getYongHuMing())==1
                ||dao.existsShouJiHao(item.getShouJiHao())==1
                ||dao.existsZhenShiXingMing(item.getZhenShiXingMing())==1){
            return RespBean.fail(-5,"关键参数重复");
        }
        return dao.addYuanGong(item)==1?RespBean.success():RespBean.fail();
    }
    public RespBean updateYuanGong(YuanGong item) {
        if (item == null
                || item.getZhenShiXingMing() == null
                || item.getId() == null
                || item.getYongHuMing() == null
                || item.getShouJiHao() == null) {
            return RespBean.fail(-6, "缺少关键参数");
        }

        YuanGong old = dao.getById(item.getId());
        if (!old.getZhenShiXingMing().equals(item.getZhenShiXingMing())
                && dao.existsZhenShiXingMing(item.getZhenShiXingMing()) == 1) {
            return RespBean.fail(-5, "关键参数重复");
        }
        if (!old.getShouJiHao().equals(item.getShouJiHao())
                &&dao.existsShouJiHao(item.getShouJiHao()) == 1 ){
            return RespBean.fail(-5, "关键参数重复");
        }
        if (!old.getYongHuMing().equals(item.getYongHuMing())
                && dao.existsYongHuMing(item.getYongHuMing()) == 1) {
            return RespBean.fail(-5, "关键参数重复");
        }
        return dao.updateYuanGong(item)==1?RespBean.success():RespBean.fail();

    }
    public RespBean deleteYuanGongById(Integer id) {
        return dao.deleteYuanGongById(id)==1?RespBean.success():RespBean.fail();
    }
    public RespBean deleteYuanGongByIds(Integer[] ids) {
        return dao.deleteYuanGongByIds(ids)>0?RespBean.success():RespBean.fail();
    }

    public RespBean initPassword(Integer id) {
        return dao.initPassword(id)==1?RespBean.success():RespBean.fail();
    }

    public int existsZhenShiXingMing(String  zhenShiXingMing) {
        return dao.existsZhenShiXingMing(zhenShiXingMing);
    }
    public int existsYongHuMing(String  yongHuMing) {
        return  dao.existsYongHuMing(yongHuMing);
    }
    public int existsShouJiHao(String  shouJiHao){
        return dao.existsShouJiHao(shouJiHao);
    }

    public List<Character> getYuanGongJueSeById(Integer id){
        return  dao.getJueSeList(id);
    }

    public int deleteYuanGongJueSeByYuanGongId(Integer yuanGongId) {
        return dao.deleteYuanGongJueSeByYuanGongId(yuanGongId);
    }
    public int addYuanGongJueSeByYuanGongId(Integer yuanGongId, Integer[] jueSeIds){
        return dao.addYuanGongJueSeByYuanGongId(yuanGongId, jueSeIds);
    }
}
