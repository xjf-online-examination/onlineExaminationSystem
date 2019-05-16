package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.logic.ExamPaperLogic;
import com.wxj.mapper.ExamPaperMapper;
import com.wxj.mapper.ExamPaperQuestionsMapper;
import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.PO.ExamPaper;
import com.wxj.model.VO.ExamPaperDetailsVO;
import com.wxj.model.VO.ExamPaperVO;
import com.wxj.service.ExamPaperServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    ExamPaperLogic examPaperLogic;

    @Override
    public List<ExamPaperVO> listExamPaperByParams(ExamPaperParamsDTO examPaperParamsDTO) {
        PageBounds pageBounds = new PageBounds(examPaperParamsDTO.getCurrentPage(), examPaperParamsDTO.getPageSize());
        return examPaperMapper.selectExamPaperByParams(examPaperParamsDTO, pageBounds);
    }

    @Override
    public int countExamPaperByParams(ExamPaperParamsDTO examPaperParamsDTO) {
        return examPaperMapper.countExamPaperByParams(examPaperParamsDTO);
    }

    @Override
    public ExamPaperDetailsVO getExamPaperDetailsById(Integer id) {
        return examPaperMapper.selectExamPaperDetailsById(id);
    }

    @Override
    public int save(ExamPaperSaveModifyDTO examPaperSaveModifyDTO) {
        //1:自动 2:手动
        int i = 0;
        switch (examPaperSaveModifyDTO.getType()) {
            case 1:
                i = examPaperLogic.automaticSave(examPaperSaveModifyDTO);
                break;
            case 2:
                i = examPaperLogic.manualSave(examPaperSaveModifyDTO);
        }
        return i;
    }

    @Override
    public int modify(ExamPaperSaveModifyDTO examPaperSaveModifyDTO) {
        //TODO:修改试卷

        //TODO:删除试卷试题对应关系

        //TODO:重新插入
        return 0;
    }
}