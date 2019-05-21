package com.wxj.model.PO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: ExamQuestions</p>
 * <p>Description:试题 </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: www.hanshow.com</p>
 * @author wangxiaojun
 * @date 2019-05-17 23:30:52
 * @version 1.0
 */
@Getter
@Setter
public class ExamQuestions implements Serializable {
    /**
    * 分页查询条件
    */
    private String whereStr;

    /**
     * ID
     */
    private Integer id;

    /**
     * 课程编号
     */
    private String courseCode;

    /**
     * 试题编号
     */
    private String code;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 删除标志
     */
    private String delFlag;
}