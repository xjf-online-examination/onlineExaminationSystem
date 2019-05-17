package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ExamQuestionsModifyDTO implements Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 课程编号
     */
    private String courseCode;
    /**
     * 试题类型
     */
    private String type;
    /**
     * 试题题目
     */
    private String title;
    /**
     * 选项A
     */
    private String optionA;
    /**
     * 选项B
     */
    private String optionB;
    /**
     * 选项C
     */
    private String optionC;
    /**
     * 选项D
     */
    private String optionD;
    /**
     * 选项E
     */
    private String optionE;
    /**
     * 单选答案
     */
    private String singleAnswer;
    /**
     * 多选答案
     */
    private String multipleAnswer;
    /**
     * 判断答案
     */
    private String yesNoAnswer;
}
