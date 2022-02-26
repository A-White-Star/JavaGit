package com.example.vshopadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShangPin {
    private Long id;
    private String pinPai;
    private String mingCheng;
    private String shangPinBianHao;
    private Float jiaGe;
    private Boolean isDelete;

    private String zhanShiTuPian;

    List<ShangPinTuPian> imgList;
}
