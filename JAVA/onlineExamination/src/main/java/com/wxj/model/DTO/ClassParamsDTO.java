package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClassParamsDTO implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 班级编号
     */
    private String code;
    /**
     * 班级名称
     */
    private String name;

    private Integer currentPage;

    private Integer pageSize;
}
