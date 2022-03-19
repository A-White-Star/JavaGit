package com.white.examsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private Long id;
    private String testNumber;
    private String testName;
    private Date date;
    private String teacherName;
    private String place;
    private Boolean isDelete;
}
