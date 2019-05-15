package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourseParamsDTO implements Serializable {
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
    private Integer classId;

    private Integer currentPage;

    private Integer pageSize;
}
