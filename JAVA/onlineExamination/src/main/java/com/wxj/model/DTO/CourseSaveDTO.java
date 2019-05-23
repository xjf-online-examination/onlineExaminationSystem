package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: CourseSaveDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-24 00:50
 */
@Getter
@Setter
public class CourseSaveDTO implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 课程编号
     */
    private String code;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 所属班级
     */
    private List<Integer> classIdList;
}