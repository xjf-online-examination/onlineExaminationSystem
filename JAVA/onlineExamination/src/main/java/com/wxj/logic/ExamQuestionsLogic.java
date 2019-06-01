package com.wxj.logic;

import com.wxj.constant.ExamConstant;
import com.wxj.exception.SystemErrorException;
import com.wxj.mapper.ExamQuestionsMapper;
import com.wxj.mapper.ExamScheduleMapper;
import com.wxj.model.DO.ExamQuestionsDO;
import com.wxj.model.DO.ExamQuestionsEntryAnswerDO;
import com.wxj.model.DTO.StudentAnswerSaveDTO;
import com.wxj.model.DTO.StudentAnswerSaveDetailsDTO;
import com.wxj.model.DTO.StudentEntryAnswerSaveDTO;
import com.wxj.model.PO.ExamSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
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

    public Map<Integer, Float> getScore(StudentAnswerSaveDTO studentAnswerSaveDTO) {
        Map<Integer, Float> scoreMap = new HashMap<>();
        ExamSchedule examSchedule = examScheduleMapper.selectByPrimaryKey(studentAnswerSaveDTO.getExamScheduleId());
        if (null == examSchedule) {
            throw new SystemErrorException("考试安排不存在");
        }
        //获取标准答案
        List<ExamQuestionsDO> examQuestionsDOList = examQuestionsMapper.selectExamPaperQuestionsDetailsAnswer(studentAnswerSaveDTO.getExamScheduleId());

        for (ExamQuestionsDO examQuestionsDO : examQuestionsDOList) {
            if (examQuestionsDO.getType().equals(ExamConstant.EXAM_QUESTIONS_TYPE_SIX)) {
                List<ExamQuestionsEntryAnswerDO> examQuestionsEntryAnswerDOList = examQuestionsMapper.selectQuestionsEntryAnswer(examQuestionsDO.getExamQuestionsId());
                examQuestionsDO.setExamQuestionsEntryAnswerDOList(examQuestionsEntryAnswerDOList);
            }
        }

        if (null == examQuestionsDOList && examQuestionsDOList.size() == 0) {
            throw new SystemErrorException("试卷不存在");
        }
        Map<Integer, ExamQuestionsDO> examQuestionsDOMap = examQuestionsDOList.stream().collect(Collectors.toMap(ExamQuestionsDO::getQuestionsNo, obj -> obj));
        for (StudentAnswerSaveDetailsDTO studentAnswerSaveDetailsDTO : studentAnswerSaveDTO.getAnswerSaveDetailsDTOList()) {
            if (examQuestionsDOMap.get(studentAnswerSaveDetailsDTO.getQuestionsNo()) != null) {
                Float score = this.computeScore(studentAnswerSaveDetailsDTO, examQuestionsDOMap.get(studentAnswerSaveDetailsDTO.getQuestionsNo()));
                scoreMap.put(studentAnswerSaveDetailsDTO.getQuestionsNo(), score);
            } else {
                scoreMap.put(studentAnswerSaveDetailsDTO.getQuestionsNo(), 0f);
            }
        }
        return scoreMap;
    }

    private Float computeScore(StudentAnswerSaveDetailsDTO studentAnswe, ExamQuestionsDO standardAnswer) {
        float score = 0f;
        //1:单选题 2:多选题 3:不定项选择 4:判断题 5:简答题 6:分录
        switch (studentAnswe.getQuestionsType()) {
            case "1":
                score = this.radio(studentAnswe.getAnswer(), standardAnswer.getSingleAnswer(), standardAnswer.getScore());
                break;
            case "2":
                score = this.multiSelect(studentAnswe.getAnswer(), standardAnswer.getMultipleAnswer(), standardAnswer.getScore());
                break;
            case "3":
                score = this.nonDirectional(studentAnswe.getAnswer(), standardAnswer.getMultipleAnswer(), standardAnswer.getScore());
                break;
            case "4":
                score = this.judge(studentAnswe.getAnswer(), standardAnswer.getYesNoAnswer(), standardAnswer.getScore());
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

    private Float multiSelect(String studentAnswer, String standardAnswer, Integer score) {
        char[] studentAnswers = studentAnswer.toUpperCase().toCharArray();
        Arrays.sort(studentAnswers);
        char[] standardAnswers = standardAnswer.toUpperCase().toCharArray();
        Arrays.sort(standardAnswers);

        String stuAnswer = String.valueOf(studentAnswers);
        String standAnswer = String.valueOf(standardAnswers);
        if (stuAnswer.length() > standAnswer.length()) {
            if (stuAnswer.equals(standAnswer)) {
                return (float) score;
            } else if (this.find(standAnswer, stuAnswer)) {
                return (float) score/2;
            } else {
                return 0f;
            }
        } else {
            return 0f;
        }
    }

    private Float nonDirectional(String studentAnswer, String standardAnswer, Integer score) {
        char[] studentAnswers = studentAnswer.toUpperCase().toCharArray();
        Arrays.sort(studentAnswers);
        char[] standardAnswers = standardAnswer.toUpperCase().toCharArray();
        Arrays.sort(standardAnswers);

        for (char standA : standardAnswers) {
            for (char studentA : studentAnswers) {
                if (standA == studentA) {
                    //TODO:
                }
            }
        }
        return (float) score;
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
        Map<Integer, ExamQuestionsEntryAnswerDO> standardAnswerMap = standardAnswer.stream().collect(Collectors.toMap(ExamQuestionsEntryAnswerDO::getRow, obj -> obj));
        //找到合计的row
        int maxRow = studentAnswer.size()-1;
        for (int i=0; i<studentAnswer.size(); i++) {
            StudentEntryAnswerSaveDTO studentEntryAnswer = studentAnswer.get(i);
            ExamQuestionsEntryAnswerDO standardEntryAnswer = standardAnswerMap.get(i);
            //行数最大的是合计
            if (standardEntryAnswer.getRow() == maxRow) {
                if (null != standardEntryAnswer.getTotal() && null != studentEntryAnswer.getTotal() && standardEntryAnswer.getTotal().equals(studentEntryAnswer.getTotal())) {
                    score += standardEntryAnswer.getTotalScore();
                }
                if (null != standardEntryAnswer.getDebitTotal() && null != studentEntryAnswer.getDebitTotal() && standardEntryAnswer.getDebitTotal().equals(studentEntryAnswer.getDebitTotal())) {
                    score += standardEntryAnswer.getDebitAmountScore();
                }
                if (null != standardEntryAnswer.getCreditTotal() && null != studentEntryAnswer.getCreditTotal() && standardEntryAnswer.getCreditTotal().equals(studentEntryAnswer.getCreditTotal())) {
                    score += standardEntryAnswer.getDebitAmountScore();
                }
            } else {
                if (null != standardEntryAnswer.getSummary() && null != studentEntryAnswer.getSummary() && standardEntryAnswer.getSummary().equals(studentEntryAnswer.getSummary())) {
                    score += standardEntryAnswer.getSummaryScore();
                }
                if (null != standardEntryAnswer.getSubject1() && null != studentEntryAnswer.getSubject1() && standardEntryAnswer.getSubject1().equals(studentEntryAnswer.getSubject1())) {
                    score += standardEntryAnswer.getSubject1Score();
                }
                if (null != standardEntryAnswer.getSubject2() && null != studentEntryAnswer.getSubject2() && standardEntryAnswer.getSubject2().equals(studentEntryAnswer.getSubject2())) {
                    score += standardEntryAnswer.getSubject2Score();
                }
                if (null != standardEntryAnswer.getDebitAmount() && null != studentEntryAnswer.getDebitAmount() && standardEntryAnswer.getDebitAmount().equals(studentEntryAnswer.getDebitAmount())) {
                    score += standardEntryAnswer.getDebitAmountScore();
                }
                if (null != standardEntryAnswer.getCreditAmount() && null != studentEntryAnswer.getCreditAmount() && standardEntryAnswer.getCreditAmount().equals(studentEntryAnswer.getCreditAmount())) {
                    score += standardEntryAnswer.getCreditAmountScore();
                }
            }
        }
        return score;
    }

    private boolean find(String source, String target) {
        char[] c1 = source.toCharArray();
        char[] c2 = target.toCharArray();
        int i = 0;
        int j;
        while (i < source.length() - 1) {
            j = 0;
            while (c1[i] == c2[j] && j < target.length() - 1) {
                i++;
                j++;
            }
            if (j == target.length() - 1) {
                return true;
            }
            i = i - j + 1;

        }
        return false;
    }
}