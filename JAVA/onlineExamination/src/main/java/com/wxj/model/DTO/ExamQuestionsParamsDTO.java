package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExamQuestionsParamsDTO implements Serializable {
    /**
     * id
     */
    private Integer id;
    /**
     * 试题编号
     */
    private String code;
    /**
     * 题目
     */
    private String title;
    /**
     * 课程编号
     */
    private String courseCode;
    /**
     * 试题类型 1:单选题 2:多选题 3:不定项选择 4:判断题 5:简答题 6:分录
     */
    private Integer type;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页大小
     */
    private Integer pageSize;
}
