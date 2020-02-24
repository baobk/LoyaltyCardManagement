package com.neo.project.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Account {

    private Integer id;
    private String name;
    private String password;
    private Date creationDate;
    private Integer creationBy;
}
