package com.wxj.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.model.DO.ExamQuestionsDO;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.DTO.QuestionsPageDTO;
import com.wxj.model.PO.ExamQuestions;
import com.wxj.model.PO.ExamQuestionsExample;
import com.wxj.model.VO.ExamQuestionsDetailsVO;
import com.wxj.model.VO.ExamQuestionsVO;
import com.wxj.model.VO.StudentExamQuestionsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamQuestionsMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(ExamQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(ExamQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(ExamQuestions record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ExamQuestions record);

    /**
     *
     * @mbg.generated
     */
    List<ExamQuestions> selectByExample(ExamQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    ExamQuestions selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ExamQuestions record, @Param("example") ExamQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ExamQuestions record, @Param("example") ExamQuestionsExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ExamQuestions record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ExamQuestions record);

    List<ExamQuestionsVO> selectExamQuestionsByParams(@Param("record")ExamQuestionsParamsDTO record, PageBounds pageBounds);

    Long countExamQuestionsByParams(@Param("record")ExamQuestionsParamsDTO record);

    ExamQuestionsDetailsVO getExamQuestionsDetailsById(Integer id);

    int batchInsert(List<ExamQuestions> examQuestionsList);

    /**
     * 供新增试卷使用
     * @param pageDTO
     * @param pageBounds
     * @return
     */
    List<ExamQuestionsDetailsVO> selectExamQuestions(@Param("record") QuestionsPageDTO pageDTO, PageBounds pageBounds);

    /**
     * 供新增试卷使用
     * @param pageDTO
     * @return
     */
    Long countExamQuestions(@Param("record") QuestionsPageDTO pageDTO);

    List<StudentExamQuestionsVO> selectStudentExamPaperDetailsById(Integer examPaperId);

    List<ExamQuestionsDO> selectExamPaperQuestionsDetailsAnswer(Integer examPaperId);
}