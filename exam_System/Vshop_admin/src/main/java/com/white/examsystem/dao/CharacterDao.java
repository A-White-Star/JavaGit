package com.white.examsystem.dao;

import com.white.examsystem.model.Character;

import java.util.List;

public interface CharacterDao {
    List<Character> getAllCharacterList();

    Character getCharacterById(Integer id);
//    int addJueSe(Character item);
//    int updateJueSe(Character item);
//    int existsJueSeMingCheng(String jueSeMingCheng);
//    int deleteJueSeById(Integer id);
//
//    int deleteJueSeByIds(Integer[] ids);

//    int deleteJueSeCaiDanByJueSeId(Integer jueSeId);
//    int addJueSeCaiDanByJueSeId(Integer jueSeId,Integer[] caiDanIds);

}
