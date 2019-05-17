package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExamPaperParamsDTO implements Serializable{
    /**
     * 试卷编号
     */
    private String code;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 课程编号
     */
    private String courseCode;
    /**
     * 出卷人
     */
    private String teacherName;

    private Integer currentPage;

    private Integer pageSize;
}
