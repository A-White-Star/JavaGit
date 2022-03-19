package com.white.examadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    private Integer characterId;
    private String characterNameEN;
    private String characterNameCN;
    private Boolean isDelete;

    private List<Character> characterList;
}