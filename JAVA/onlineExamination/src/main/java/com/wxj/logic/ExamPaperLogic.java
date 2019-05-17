package com.wxj.logic;

import com.google.common.collect.Lists;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.mapper.ExamPaperMapper;
import com.wxj.mapper.ExamPaperQuestionsMapper;
import com.wxj.model.DTO.ExamPaperSaveModifyDTO;
import com.wxj.model.PO.ExamPaper;
import com.wxj.model.PO.ExamPaperQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title: ExamPaperLogic</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: www.hanshow.com</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-16 01:01
 */
@Component
public class ExamPaperLogic {
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
    public int automaticSave(ExamPaperSaveModifyDTO examPaperSaveModifyDTO) {
        return 0;
    }

    /**
     * 手动生成试卷
     * @param examPaperSaveModifyDTO
     * @return
     */
    @Transactional
    public int manualSave(ExamPaperSaveModifyDTO examPaperSaveModifyDTO) {
        ExamPaper examPaper = new ExamPaper();
        int i = examPaperMapper.insert(examPaper);

        List<ExamPaperQuestions> examPaperQuestionsList = Lists.newArrayList();
        int j = examPaperQuestionsMapper.batchInsert(examPaperQuestionsList);

        if (SystemConstant.ZERO == i || j != examPaperSaveModifyDTO.getExamPaperQuestionsDTOList().size()) {
            throw new OperationException(" 插入失败");
        }
        return i;
    }
}