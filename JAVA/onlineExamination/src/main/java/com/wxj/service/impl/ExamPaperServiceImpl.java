package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.OperationException;
import com.wxj.logic.ExamPaperLogic;
import com.wxj.mapper.ExamPaperMapper;
import com.wxj.mapper.ExamPaperQuestionsMapper;
import com.wxj.mapper.TeacherMapper;
import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.DTO.ExamPaperQuestionsDTO;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.PO.*;
import com.wxj.model.VO.ExamPaperDetailsVO;
import com.wxj.model.VO.ExamPaperVO;
import com.wxj.service.ExamPaperServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    Logger logger = LoggerFactory.getLogger(ExamPaperServiceImpl.class);
    @Autowired
    ExamPaperMapper examPaperMapper;
    @Autowired
    ExamPaperLogic examPaperLogic;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    ExamPaperQuestionsMapper examPaperQuestionsMapper;

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
    public int save(ExamPaperSaveModifyDTO examPaperSaveModifyDTO, String userCode) throws BusinessRuntimeException {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andJobNoEqualTo(userCode);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        //1:自动 2:手动
        int i = 0;
        switch (examPaperSaveModifyDTO.getType()) {
            case 1:
                i = examPaperLogic.automaticSave(teacherList.get(0).getId(), examPaperSaveModifyDTO);
                break;
            case 2:
                i = examPaperLogic.manualSave(teacherList.get(0).getId(), examPaperSaveModifyDTO);
        }
        return i;
    }

    @Transactional
    @Override
    public int modify(ExamPaperSaveModifyDTO examPaperSaveModifyDTO, String userCode) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andJobNoEqualTo(userCode);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        //修改试卷
        int examPaperUpdateSize = 0;
        int examPaperQuestionsDeleteSize = 0;
        int examPaperQuestionsInsertSize = 0;
        try {
            ExamPaper examPaper = new ExamPaper();
            BeanUtils.copyProperties(examPaperSaveModifyDTO, examPaper);
            examPaper.setOpeator(teacherList.get(0).getId());
            examPaper.setModifyTime(new Date());
            examPaperUpdateSize = examPaperMapper.updateByPrimaryKeySelective(examPaper);
            //删除试卷试题对应关系
            ExamPaperQuestionsExample examPaperQuestionsExample = new ExamPaperQuestionsExample();
            examPaperQuestionsExample.createCriteria().andExamPaperIdEqualTo(examPaper.getId());
            examPaperQuestionsDeleteSize = examPaperQuestionsMapper.deleteByExample(examPaperQuestionsExample);
            //重新插入
            List<ExamPaperQuestions> examPaperQuestionsList = examPaperLogic.assembleExamPaperQuestionsList(teacherList.get(0).getId(), examPaperSaveModifyDTO.getExamPaperQuestionsDTOList());
            examPaperQuestionsInsertSize = examPaperQuestionsMapper.batchInsert(examPaperQuestionsList);
        } catch (Exception e) {
            logger.error("com.wxj.service.impl.ExamPaperServiceImpl.modify", e);
            throw new OperationException("修改试卷失败");
        }
        if (examPaperUpdateSize == 0 || examPaperQuestionsDeleteSize != examPaperSaveModifyDTO.getExamPaperQuestionsDTOList().size() || examPaperQuestionsInsertSize != examPaperQuestionsInsertSize) {
            throw new OperationException("修改试卷失败");
        }
        return 0;
    }
}