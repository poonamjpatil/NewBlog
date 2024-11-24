package com.newblog.dto;

import lombok.Data;

import javax.persistence.Entity;


@Data
public class SignUp {

    private String name;
    private String email;
    private String username;
    private String password;

}
