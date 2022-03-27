package com.white.examsystem.service;

import com.white.examsystem.common.RespBean;
import com.white.examsystem.dao.CharacterDao;
import com.white.examsystem.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    @Autowired
    CharacterDao characterDao;
    public List<Character> getAllJueSeList(){
        return  characterDao.getAllCharacterList();
    }

    public Character getJueSeById(Integer id){
        return characterDao.getCharacterById(id);
    }
//    public RespBean saveJueSe(Character item){
//        if(characterDao.existsCharacterName(item.getCha())==1){
//            return RespBean.fail(-5,"角色名称重复！");
//        }else if(dao.addJueSe(item)==1){
//            return RespBean.success();
//        }else{
//            return RespBean.fail();
//        }
//    }
//
//    public RespBean updateJueSe(Character item){
//       return dao.updateJueSe(item)==1?RespBean.success():RespBean.fail();
//    }
//
//    public RespBean deleteJueSeById(Integer id){
//        return dao.deleteJueSeById(id) == 1?RespBean.success():RespBean.fail();
//    }
//
//    public RespBean deleteJueSeByIds(Integer[] ids) {
//        return dao.deleteJueSeByIds(ids) == 1?RespBean.success():RespBean.fail();
//    }
    public RespBean updateCharacterMenu(Integer characterId, Integer[] menuIds){
        characterDao.deleteCharacherMenuByCharacterId(characterId);
        characterDao.addCharacterMenuByCharacterId(characterId, menuIds);
        return RespBean.success();
    }
}
