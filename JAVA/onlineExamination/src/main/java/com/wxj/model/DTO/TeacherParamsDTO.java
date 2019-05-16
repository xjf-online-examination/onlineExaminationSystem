package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TeacherParamsDTO implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     *  工号
     */
    private String jobNo;
    /**
     * 姓名
     */
    private String name;

    private Integer currentPage;

    private Integer pageSize;
}
