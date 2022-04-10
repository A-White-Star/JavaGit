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
    CharacterDao dao;
    public List<Character> getAllCharacterList(){
        return  dao.getAllCharacterList();
    }

    public Character getCharacterById(Integer id){
        return dao.getCharacterById(id);
    }
}
