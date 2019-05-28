package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: PageDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-24 17:39
 */
@Getter
@Setter
public class QuestionsPageDTO implements Serializable {
    /**
     * 课程编号
     */
    private String courseCode;
    /**
     * 试题编号
     */
    private String questionsCode;
    /**
     * 题目，模糊
     */
    private String questionsTitle;
    /**
     * 试题类型1:单选题 2:多选题 3:不定项选择 4:判断题 5:简答题 6:分录
     */
    private String questionsType;

    private Integer currentPage;

    private Integer pageSize;
}