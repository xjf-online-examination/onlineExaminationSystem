package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class LoginDTO implements Serializable {

    private Integer userType;

    private String username;

    private String password;
}
