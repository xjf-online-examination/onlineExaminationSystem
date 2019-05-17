package com.wxj.model.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Title: ExamPaperQuestionsDTO</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-16 00:47
 */
@Getter
@Setter
public class ExamPaperQuestionsDTO implements Serializable {
    /**
     * 试题ID
     */
    private Integer examQuestionsId;
    /**
     * 题号
     */
    private Byte questionsNo;

}