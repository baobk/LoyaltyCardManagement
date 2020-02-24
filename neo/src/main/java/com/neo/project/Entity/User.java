package com.neo.project.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer userId;                 // VAR OF USER ID
    private Integer roleId;                 // VAR OF ROLE ID
    private Integer accountId;              // VAR OF ACCOUNT ID
    private String name;
    private Integer sex;
    private Date birthday;
    private String email;

}
