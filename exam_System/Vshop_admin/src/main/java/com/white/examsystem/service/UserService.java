package com.white.examsystem.service;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.dao.UserDao;
import com.white.examsystem.model.Character;
import com.white.examsystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=dao.loadUserByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        List<Character> characterList=dao.getCharacterList(user.getId());
        user.setCharacterList(characterList);
        return user;
    }
    //拉取所有员工
    public List<User> getAll(){
        return dao.getAll();
    }
    public List<User> getListByKey(String key){
        return dao.getListByKey(key);
    }
    public User getById(Integer id){
        return dao.getById(id);
    }
    //增删改、批量删除
    public RespBean addUser(User item){
        if(item==null
                ||item.getName()==null
                ||item.getUsername()==null
                ||item.getPhone()==null){
            return RespBean.fail(-6,"缺少关键参数");
        }
        if(dao.existsUserName(item.getUsername())==1
                ||dao.existsPhone(item.getPhone())==1){
				            return RespBean.fail(-5,"关键参数重复");
        }
        return dao.addUser(item)==1?RespBean.success():RespBean.fail();
    }
    public RespBean updateUser(User item) {
        if (item == null
                || item.getName() == null
                || item.getId() == null
                || item.getUsername() == null
                || item.getPhone() == null) {
            return RespBean.fail(-6, "缺少关键参数");
        }

        User old = dao.getById(item.getId());
        if (!old.getPhone().equals(item.getPhone())
                &&dao.existsPhone(item.getPhone()) == 1 ){
            return RespBean.fail(-5, "关键参数重复");
        }
        if (!old.getUsername().equals(item.getUsername())
                && dao.existsUserName(item.getUsername()) == 1) {
            return RespBean.fail(-5, "关键参数重复");
        }
        return dao.updateUser(item)==1?RespBean.success():RespBean.fail();

    }
    public RespBean deleteUserById(Integer id) {
        return dao.deleteUserById(id)==1?RespBean.success():RespBean.fail();
    }
    public RespBean deleteUserByIds(Integer[] ids) {
        return dao.deleteUserByIds(ids)>0?RespBean.success():RespBean.fail();
    }

    public RespBean initPassword(Integer id) {
        return dao.initPassword(id)==1?RespBean.success():RespBean.fail();
    }


    public int existsUserName(String  userName) {
        return  dao.existsUserName(userName);
    }
    public int existsPhone(String  Phone){
        return dao.existsPhone(Phone);
    }

    public List<Character> getUserCharacterById(Integer id){
        return  dao.getCharacterList(id);
    }

    public int deleteUserCharacterByUserId(Integer userId) {
        return dao.deleteUserCharacterByUserId(userId);
    }
    public int addUserCharacterByUserId(Integer userId, Integer[] characterIds){
        return dao.addUserCharacterByUserId(userId, characterIds);
    }
}
