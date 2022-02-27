package com.white.examadmin.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private  Long userId;
    private String password;
    private String userName;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //private Date registerTime;
    private Boolean isDelete;
}
