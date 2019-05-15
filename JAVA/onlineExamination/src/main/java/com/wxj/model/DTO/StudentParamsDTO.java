package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class StudentParamsDTO implements Serializable {
    /**
     * id
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

    private Integer currentPage;

    private Integer pageSize;
}
