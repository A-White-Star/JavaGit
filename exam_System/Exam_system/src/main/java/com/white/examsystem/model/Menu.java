package com.white.examadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private Integer menuId;
    private String menuName;
    private String url;
    private String path;
    private Integer subMenuId;
    //private Integer sort;
    private Boolean isDelete;
   // private String icon;
   private List<Menu>subMenu;

   private List<Character> jueSeList;
}
