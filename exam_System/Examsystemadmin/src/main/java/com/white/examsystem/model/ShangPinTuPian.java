package com.white.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShangPinTuPian {
    private Long id;
    private Long shangPinId;
    private String imgUrl;
    private Boolean isDelete;
}
