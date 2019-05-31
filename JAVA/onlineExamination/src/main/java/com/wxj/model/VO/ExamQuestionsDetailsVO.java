package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class ExamQuestionsDetailsVO implements Serializable {
    /**
     * 题号
     */
    private Byte questionsNo;
    /**
     * ID
     */
    private int id;
    /**
     * 试题编号
     */
    private String code;
    /**
     * 课程编号
     */
    private String courseCode;
    /**
     * 课程名称
     */
    private String courseName;
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
    /**
     * 分数
     */
    private Integer score;
    /**
     * 分录答案
     */
    private List<EntryStandardAnswerDetailsVO> entryStandardAnswerDetailsVOList;

}
