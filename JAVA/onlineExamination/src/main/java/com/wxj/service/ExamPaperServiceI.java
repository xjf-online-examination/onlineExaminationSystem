package com.wxj.service;

import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.VO.ExamPaperDetailsVO;
import com.wxj.model.VO.ExamPaperVO;
import com.wxj.model.VO.StudentExamQuestionsVO;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: ExamPaperServiceI</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-15 22:41
 */
public interface ExamPaperServiceI {

    List<ExamPaperVO> listExamPaperByParams(ExamPaperParamsDTO examPaperParamsDTO);

    int countExamPaperByParams(ExamPaperParamsDTO examPaperParamsDTO);

    Map<String, List<StudentExamQuestionsVO>> getStudentExamPaperDetailsById(Integer id);

    ExamPaperDetailsVO getTeacherExamPaperDetailsById(Integer id);

    int save(ExamPaperSaveModifyDTO examPaperSaveModifyDTO, String userCode);

    int modify(ExamPaperSaveModifyDTO examPaperSaveModifyDTO, String userCode);

    void delete(Integer examPaperId);

}