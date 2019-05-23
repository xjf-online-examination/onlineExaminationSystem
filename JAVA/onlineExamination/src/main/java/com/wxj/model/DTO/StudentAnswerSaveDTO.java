package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: StudentAnswerSaveDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-24 00:21
 */
@Setter
@Getter
public class StudentAnswerSaveDTO implements Serializable {
    /**
     * 学号
     */
    private String sno;
    /**
     * 考试安排id
     */
    private Integer examScheduleId;
    /**
     * 题号
     */
    private Integer questionsNo;
    /**
     * 题目类型
     */
    private String questionsType;
    /**
     * 答案
     */
    private String answer;
    /**
     * 分录答案
     */
    private List<StudentEntryAnswerSaveDTO> studentEntryAnswerSaveDTOList;
}