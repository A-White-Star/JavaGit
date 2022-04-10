package com.white.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer id;
    private String name;
    private String url;
    private String path;
    private Integer fuMenuId;
    private Integer sort;
    private Boolean isDelete;
    private String icon;

    private List<Menu> subMenu;

    private List<Character> characterList;
}
