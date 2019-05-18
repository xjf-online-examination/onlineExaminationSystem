package com.wxj.model.PO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: UserInfo</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-08 21:52
 */

@Getter
@Setter
public class UserInfo implements Serializable {
    private int id;

    private String name;

    private String code;
}