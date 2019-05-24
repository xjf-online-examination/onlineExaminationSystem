package com.wxj.logic;

import com.wxj.exception.SystemErrorException;
import com.wxj.mapper.ExamQuestionsMapper;
import com.wxj.mapper.ExamScheduleMapper;
import com.wxj.model.DO.ExamQuestionsDO;
import com.wxj.model.DO.ExamQuestionsEntryAnswerDO;
import com.wxj.model.DTO.StudentAnswerSaveDetailsDTO;
import com.wxj.model.DTO.StudentEntryAnswerSaveDTO;
import com.wxj.model.PO.ExamSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Title: ExamQuestionsLogic</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-24 21:58
 */
@Component
public class ExamQuestionsLogic {
    @Autowired
    ExamScheduleMapper examScheduleMapper;
    @Autowired
    ExamQuestionsMapper examQuestionsMapper;

    public Float getScore(Integer examScheduleId, StudentAnswerSaveDetailsDTO studentAnswerSaveDetailsDTO) {
        ExamSchedule examSchedule = examScheduleMapper.selectByPrimaryKey(examScheduleId);
        if (null == examSchedule) {
            throw new SystemErrorException("考试安排不存在");
        }
        //获取标准答案
        Map<String, Object> paramsMap = new HashMap<>(2);
        paramsMap.put("examPaperId", examScheduleId);
        paramsMap.put("questionsNo", studentAnswerSaveDetailsDTO.getQuestionsNo());
        List<ExamQuestionsDO> examQuestionsDOList = examQuestionsMapper.selectExamPaperQuestionsDetails(paramsMap);
        if (null == examQuestionsDOList && examQuestionsDOList.size() == 0) {
            throw new SystemErrorException("试卷不存在");
        }
        ExamQuestionsDO examQuestionsDO = examQuestionsDOList.get(0);
        //如果题型不同直接返回0分
        if (!studentAnswerSaveDetailsDTO.getQuestionsType().equals(examQuestionsDO.getType())) {
            return 0f;
        }
        return this.computeScore(studentAnswerSaveDetailsDTO, examQuestionsDO);
    }

    private Float computeScore(StudentAnswerSaveDetailsDTO studentAnswe, ExamQuestionsDO standardAnswer) {
        float score = 0f;
        switch (studentAnswe.getQuestionsType()) {
            case "1":
                score = this.radio(studentAnswe.getAnswer(), standardAnswer.getSingleAnswer(), standardAnswer.getScore());
                break;
            case "2":
                //TODO:
                break;
            case "3":
                //TODO:
                break;
            case "4":
                score = this.judge(studentAnswe.getAnswer(), standardAnswer.getSingleAnswer(), standardAnswer.getScore());
                break;
            case "5":
                break;
            case "6":
                score = this.entry(studentAnswe.getStudentEntryAnswerSaveDTOList(), standardAnswer.getExamQuestionsEntryAnswerDOList());
                break;
        }
        return score;
    }

    private Float radio(String studentAnswer, String standardAnswer, Integer score) {
        if (standardAnswer.equals(studentAnswer)) {
            return (float) score;
        } else {
            return 0f;
        }
    }


    private Float judge(String studentAnswer, String standardAnswer, Integer score) {
        if (standardAnswer.equals(studentAnswer)) {
            return (float) score;
        } else {
            return 0f;
        }
    }

    private Float entry(List<StudentEntryAnswerSaveDTO> studentAnswer, List<ExamQuestionsEntryAnswerDO> standardAnswer) {
        float score = 0f;
        Map<Integer, ExamQuestionsEntryAnswerDO> standardAnswerMap = standardAnswer.stream().collect(Collectors.toMap(ExamQuestionsEntryAnswerDO::getRow, obj->obj));
        //找到合计的row
        int maxRow = studentAnswer.stream().max(Comparator.comparingInt(StudentEntryAnswerSaveDTO::getRow)).get().getRow();
        for (StudentEntryAnswerSaveDTO studentEntryAnswer : studentAnswer) {
            ExamQuestionsEntryAnswerDO standardEntryAnswer = standardAnswerMap.get(studentEntryAnswer.getRow());
            //TODO:
            //行数最大的是合计
            if (studentEntryAnswer.getRow() == maxRow) {
                if (standardEntryAnswer.getTotal().equals(studentEntryAnswer.getTotal())) {
                    score += standardEntryAnswer.getTotalScore();
                }
                if (standardEntryAnswer.getDebitTotal().equals(studentEntryAnswer.getDebitTotal())) {
                    score += standardEntryAnswer.getDebitAmountScore();
                }
                if (standardEntryAnswer.getCreditTotal().equals(studentEntryAnswer.getCreditTotal())) {
                    score += standardEntryAnswer.getDebitAmountScore();
                }
            }
        }
        return score;
    }

}