package com.wxj.service;

import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.ExamQuestionsSaveDTO;
import com.wxj.model.DTO.PageDTO;
import com.wxj.model.PO.ExamQuestions;
import com.wxj.model.VO.ExamQuestionsDetailsVO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.model.VO.SubjectOneVO;

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

    ExamQuestionsDetailsVO getExamQuestionsDetailsById(Integer id);

    int save(ExamQuestionsSaveDTO examQuestionsSaveDTO);

    int modify(ExamQuestionsSaveDTO examQuestionsSaveDTO);

    int delete(Integer id);

    int examQuestionsImport(List<ExamQuestions> examQuestionsList);

    List<ExamQuestionsDetailsVO> listExamQuestions(PageDTO pageDTO);

    Long countExamQuestions();

    /**
     * 查询一级科目列表(供新增试题使用)
     * @return
     */
    List<SubjectOneVO> listSubjectOne();
}