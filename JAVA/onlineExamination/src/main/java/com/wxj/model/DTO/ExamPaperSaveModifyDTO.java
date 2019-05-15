package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: ExamPaperSaveModifyDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-16 00:40
 */
@Getter
@Setter
public class ExamPaperSaveModifyDTO implements Serializable {
    private Integer id;

    private String code;

    private String name;

    private String courseCode;

    private Integer opeator;
    /**
     * 1:自动 2:手动
     */
    private Integer type;

    private List<ExamPaperQuestionsDTO> examPaperQuestionsDTOList;
}