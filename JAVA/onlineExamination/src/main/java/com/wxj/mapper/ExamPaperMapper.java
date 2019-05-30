package com.wxj.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.model.DTO.ExamPaperParamsDTO;
import com.wxj.model.PO.ExamPaper;
import com.wxj.model.PO.ExamPaperExample;
import com.wxj.model.VO.EntryStandardAnswerDetailsVO;
import com.wxj.model.VO.ExamPaperDetailsVO;
import com.wxj.model.VO.ExamPaperVO;
import com.wxj.model.VO.ExamQuestionsDetailsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamPaperMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(ExamPaperExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(ExamPaperExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(ExamPaper record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ExamPaper record);

    /**
     *
     * @mbg.generated
     */
    List<ExamPaper> selectByExample(ExamPaperExample example);

    /**
     *
     * @mbg.generated
     */
    ExamPaper selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ExamPaper record, @Param("example") ExamPaperExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ExamPaper record, @Param("example") ExamPaperExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ExamPaper record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ExamPaper record);

    List<ExamPaperVO> selectExamPaperByParams(@Param("record")ExamPaperParamsDTO examPaperParamsDTO, PageBounds pageBounds);

    int countExamPaperByParams(@Param("record")ExamPaperParamsDTO examPaperParamsDTO);

    ExamPaperDetailsVO selectExamPaperDetailsById(Integer id);

    List<ExamQuestionsDetailsVO> selectExamQuestions(Integer examPaperId);

    List<EntryStandardAnswerDetailsVO> selectEntryAnswer(Integer examQuestionsId);
}