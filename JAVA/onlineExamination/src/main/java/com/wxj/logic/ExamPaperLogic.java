package com.wxj.logic;

import com.google.common.collect.Lists;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.mapper.ExamPaperMapper;
import com.wxj.mapper.ExamPaperQuestionsMapper;
import com.wxj.model.DTO.ExamPaperQuestionsDTO;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.PO.ExamPaper;
import com.wxj.model.PO.ExamPaperQuestions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: ExamPaperLogic</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-16 01:01
 */
@Component
public class ExamPaperLogic {
    Logger logger = LoggerFactory.getLogger(ExamPaperLogic.class);
    @Autowired
    ExamPaperMapper examPaperMapper;

    @Autowired
    ExamPaperQuestionsMapper examPaperQuestionsMapper;

    /**
     * 自动生成试卷
     * @param examPaperSaveModifyDTO
     * @return
     */
    @Transactional
    public int automaticSave(Integer teacherId, ExamPaperSaveModifyDTO examPaperSaveModifyDTO) {
        return 0;
    }

    /**
     * 手动生成试卷
     * @param examPaperSaveModifyDTO
     * @return
     */
    @Transactional
    public int manualSave(Integer teacherId, ExamPaperSaveModifyDTO examPaperSaveModifyDTO) {
        Date date = new Date();
        ExamPaper examPaper = new ExamPaper();

        examPaper.setCode(examPaperSaveModifyDTO.getCode());
        examPaper.setName(examPaperSaveModifyDTO.getName());
        Integer questionsAmount = examPaperSaveModifyDTO.getExamPaperQuestionsDTOList().size();
        examPaper.setQuestionsAmount(questionsAmount.byteValue());
        examPaper.setCourseCode(examPaperSaveModifyDTO.getCourseCode());
        examPaper.setOpeator(teacherId);
        examPaper.setCreateTime(date);
        examPaper.setModifyTime(date);
        examPaper.setDelFlag(SystemConstant.NOUGHT);

        int i = 0;
        int j = 0;
        try {
            i = examPaperMapper.insert(examPaper);

            List<ExamPaperQuestions> examPaperQuestionsList = this.assembleExamPaperQuestionsList(examPaper.getId(), examPaperSaveModifyDTO.getExamPaperQuestionsDTOList());
            j = examPaperQuestionsMapper.batchInsert(examPaperQuestionsList);
        } catch (Exception e) {
            logger.error("com.wxj.logic.ExamPaperLogic.manualSave", e);
            throw new OperationException(" 插入失败");
        }

        if (SystemConstant.ZERO == i || j != examPaperSaveModifyDTO.getExamPaperQuestionsDTOList().size()) {
            throw new OperationException(" 插入失败");
        }
        return i;
    }

    public List<ExamPaperQuestions> assembleExamPaperQuestionsList(Integer examPaperId, List<ExamPaperQuestionsDTO> examPaperQuestionsDTOList) {
        Date date = new Date();
        List<ExamPaperQuestions> examPaperQuestionsList = Lists.newArrayList();
        ExamPaperQuestions examPaperQuestions;
        for (ExamPaperQuestionsDTO examPaperQuestionsDTO : examPaperQuestionsDTOList) {
            examPaperQuestions = new ExamPaperQuestions();
            examPaperQuestions.setExamPaperId(examPaperId);
            examPaperQuestions.setExamQuestionsId(examPaperQuestionsDTO.getExamQuestionsId());
            examPaperQuestions.setQuestionsNo(examPaperQuestionsDTO.getQuestionsNo());
            examPaperQuestions.setCreateTime(date);
            examPaperQuestions.setModifyTime(date);
            examPaperQuestions.setDelFlag(SystemConstant.NOUGHT);
            examPaperQuestionsList.add(examPaperQuestions);
        }
        return examPaperQuestionsList;
    }
}