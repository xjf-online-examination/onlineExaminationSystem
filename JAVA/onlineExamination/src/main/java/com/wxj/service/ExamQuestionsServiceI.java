package com.wxj.service;

import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.VO.ExamQuestionsVO;

import java.util.List;

/**
 * <p>Title: ExamQuestionsServiceI</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-14 23:32
 */
public interface ExamQuestionsServiceI {

    List<ExamQuestionsVO> listExamQuestionsByParams(ExamQuestionsParamsDTO examQuestionsParamsDTO);

    Long countExamQuestionsByParams(ExamQuestionsParamsDTO examQuestionsParamsDTO);

    ExamQuestionsVO getExamQuestionsDetailsById(Integer id);

    int save(ExamQuestionsSaveDTO examQuestionsSaveDTO);
}