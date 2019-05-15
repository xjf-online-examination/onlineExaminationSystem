package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClassVO implements Serializable {
    /**
     * 班级ID
     */
    private int id;
    /**
     * 班级编号
     */
    private String code;
    /**
     * 班级名称
     */
    private String name;
    /**
     * 人数
     */
    private Long number;
}
