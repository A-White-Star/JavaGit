package com.example.vshopadmin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YongHu {
    private  Long id;
    private String miMa;
    private String yongHuMing;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zhuCeShiJian;
    private Boolean shiFouJinYong;
    private Boolean isDelete;
}
