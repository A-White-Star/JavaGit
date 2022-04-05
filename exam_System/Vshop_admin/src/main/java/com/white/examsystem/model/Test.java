package com.white.examsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    private Long id;
    private String testNumber;
    private String testName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String teacherName;
    private String place;
    private Boolean isDelete;
}
