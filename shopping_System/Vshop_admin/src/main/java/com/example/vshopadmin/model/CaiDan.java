package com.example.vshopadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaiDan {
    private Integer id;
    private String mingCheng;
    private String url;
    private String path;
    private Integer fuCaiDanId;
    private Integer paiXu;
    private Boolean isDelete;
    private String icon;

    private List<CaiDan>ziCaiDan;

    private List<JueSe> jueSeList;
}
