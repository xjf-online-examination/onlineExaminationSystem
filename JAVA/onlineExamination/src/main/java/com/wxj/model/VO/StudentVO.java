package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentVO implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 学号
     */
    private String sno;
    /**
     *姓名
     */
    private String name;
    /**
     *班级ID
     */
    private Integer classId;
    /**
     * 班级名称
     */
    private String className;
}
