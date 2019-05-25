package com.wxj.model.DO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: ExamQuestionsDO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-24 23:46
 */
@Getter
@Setter
public class ExamQuestionsDO implements Serializable {
    /**
     * 试卷ID
     */
    private Integer examPaperId;
    /**
     * 试题ID
     */
    private Integer examQuestionsId;
    /**
     * 题号
     */
    private Integer questionsNo;
    /**
     * 试题类型1:单选题 2:多选题 3:不定项选择 4:判断题 5:简答题 6:分录
     */
    private String type;
    /**
     * 试题题目
     */
    private String title;
    /**
     * 选项A
     */
    private String optiona;
    /**
     * 选项B
     */
    private String optionb;
    /**
     * 选项C
     */
    private String optionc;
    /**
     * 选项D
     */
    private String optiond;
    /**
     * 选项E
     */
    private String optione;
    /**
     * 单选答案
     */
    private String singleAnswer;
    /**
     * 多选答案
     */
    private String multipleAnswer;
    /**
     * 判断题答案
     */
    private String yesNoAnswer;
    /**
     * 分数
     */
    private Integer score;
    /**
     * 分录标准答案
     */
    private List<ExamQuestionsEntryAnswerDO> examQuestionsEntryAnswerDOList;
}