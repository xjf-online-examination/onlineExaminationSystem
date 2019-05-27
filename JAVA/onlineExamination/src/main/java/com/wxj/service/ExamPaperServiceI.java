package com.wxj.service;

import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.VO.ExamPaperDetailsVO;
import com.wxj.model.VO.ExamPaperVO;

import java.util.List;

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

    ExamPaperDetailsVO getExamPaperDetailsById(Integer id);

    int save(ExamPaperSaveModifyDTO examPaperSaveModifyDTO, String userCode);

    int modify(ExamPaperSaveModifyDTO examPaperSaveModifyDTO, String userCode);

}