package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExamPaperVO implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 试卷编号
     */
    private String code;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 题量
     */
    private int number;
    /**
     * 所属课程
     */
    private String courseName;
    /**
     * 出卷人
     */
    private String teacherName;
}
