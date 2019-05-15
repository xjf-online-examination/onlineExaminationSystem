package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>Title: UserInfoVO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-08 20:41
 */

@Getter
@Setter
@ToString
public class UserInfoVO implements Serializable {
    private String securityKey;

    private String username;

    private Integer userType;
}