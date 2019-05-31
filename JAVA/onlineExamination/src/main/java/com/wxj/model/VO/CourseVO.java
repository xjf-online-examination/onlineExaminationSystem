package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CourseVO implements Serializable {
    /**
     * 课程ID
     */
    private int id;
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
    private List<ClassSelectVO> className;
}
