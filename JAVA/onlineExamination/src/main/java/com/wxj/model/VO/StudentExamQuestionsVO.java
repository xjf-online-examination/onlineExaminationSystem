package com.wxj.model.VO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: StudentExamQuestionsVO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-30 17:15
 */
@Setter
@Getter
public class StudentExamQuestionsVO implements Serializable {
    /**
     * 题号
     */
    private Integer questionsNo;
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
     * 分数
     */
    private Integer score;
    /**
     * 分录有几行
     */
    private Integer row;
}