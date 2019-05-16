package com.wxj.service;

import com.wxj.model.DTO.ExamScheduleParamsDTO;
import com.wxj.model.VO.ExamScheduleVO;

import java.util.List;

/**
 * <p>Title: ExamScheduleServiceI</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-17 00:11
 */
public interface ExamScheduleServiceI {

    List<ExamScheduleVO> listExamScheduleByParams(ExamScheduleParamsDTO examScheduleParamsDTO);

    Long countExamScheduleByParams(ExamScheduleParamsDTO examScheduleParamsDTO);
}