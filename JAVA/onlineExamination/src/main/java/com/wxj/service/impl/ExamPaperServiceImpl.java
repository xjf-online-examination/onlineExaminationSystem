package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.mapper.ExamPaperMapper;
import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.VO.ExamPaperVO;
import com.wxj.service.ExamPaperServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: ExamPaperServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-15 22:42
 */
@Service
public class ExamPaperServiceImpl implements ExamPaperServiceI {
    @Autowired
    ExamPaperMapper examPaperMapper;

    @Override
    public List<ExamPaperVO> listExamPaperByParams(ExamPaperParamsDTO examPaperParamsDTO) {
        PageBounds pageBounds = new PageBounds(examPaperParamsDTO.getCurrentPage(), examPaperParamsDTO.getPageSize());
        return examPaperMapper.selectExamPaperByParams(examPaperParamsDTO, pageBounds);
    }

    @Override
    public Long countExamPaperByParams(ExamPaperParamsDTO examPaperParamsDTO) {
        return examPaperMapper.countExamPaperByParams(examPaperParamsDTO);
    }
}