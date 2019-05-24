package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: StudentAnswerSaveDetailsDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-24 20:30
 */
@Getter
@Setter
public class StudentAnswerSaveDetailsDTO implements Serializable {
    /**
     * 题号
     */
    private Integer questionsNo;
    /**
     * 题目类型
     */
    private String questionsType;
    /**
     * 答案1:单选题 2:多选题 3:不定项选择 4:判断题 5:简答题 6:分录
     */
    private String answer;
    /**
     * 分录答案
     */
    private List<StudentEntryAnswerSaveDTO> studentEntryAnswerSaveDTOList;
}