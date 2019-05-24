package com.wxj.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.model.DO.ExamQuestionsDO;
import com.wxj.model.DTO.ExamQuestionsParamsDTO;
import com.wxj.model.PO.ExamQuestions;
import com.wxj.model.PO.ExamQuestionsExample;
import com.wxj.model.VO.ExamQuestionsDetailsVO;
import com.wxj.model.VO.ExamQuestionsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<ExamQuestionsDetailsVO> selectExamQuestions(PageBounds pageBounds);

    Long countExamQuestions();

    List<ExamQuestionsDO> selectExamPaperQuestionsDetails(Map<String, Object> map);
}