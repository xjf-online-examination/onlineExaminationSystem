package com.wxj.mapper;

import com.wxj.model.PO.ExamPaper;
import com.wxj.model.PO.ExamPaperExample;
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
}