package com.white.examsystem.dao;

import com.white.examsystem.model.Character;

import java.util.List;

public interface CharacterDao {
    List<Character> getAllCharacterList();

    Character getCharacterById(Integer id);
//    int addCharacter(Character item);
//    int updateJueSe(Character item);
    int existsCharacterName(String characterName);
//    int deleteJueSeById(Integer id);
//
//    int deleteJueSeByIds(Integer[] ids);

    int deleteCharacherMenuByCharacterId(Integer characterId);
    int addCharacterMenuByCharacterId(Integer characterId,Integer[] MenuIds);

}
