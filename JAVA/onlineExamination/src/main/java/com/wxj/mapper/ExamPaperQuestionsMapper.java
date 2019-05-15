package com.wxj.mapper;

import com.wxj.model.PO.ExamPaperQuestions;
import com.wxj.model.PO.ExamPaperQuestionsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamPaperQuestionsMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(ExamPaperQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(ExamPaperQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(ExamPaperQuestions record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ExamPaperQuestions record);

    /**
     *
     * @mbg.generated
     */
    List<ExamPaperQuestions> selectByExample(ExamPaperQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    ExamPaperQuestions selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ExamPaperQuestions record, @Param("example") ExamPaperQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ExamPaperQuestions record, @Param("example") ExamPaperQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ExamPaperQuestions record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ExamPaperQuestions record);
}