package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: ClassCourseSelectVO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-22 01:06
 */
@Getter
@Setter
public class ClassCourseSelectVO implements Serializable {
    private Integer id;

    private String className;

    private String courseName;
}