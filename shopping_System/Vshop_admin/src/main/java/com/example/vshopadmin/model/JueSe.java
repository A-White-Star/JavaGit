package com.example.vshopadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JueSe {
    private Integer id;
    private String jueSeMingCheng;
    private String xianShiMingCheng;
    private Boolean isDelete;

    private List<JueSe> jueSeList;
}
