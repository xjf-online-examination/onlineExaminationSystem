package com.wxj.mapper;

import com.wxj.model.PO.EntryStandardAnswer;
import com.wxj.model.PO.EntryStandardAnswerExample;
import com.wxj.model.VO.EntryStandardAnswerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntryStandardAnswerMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(EntryStandardAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(EntryStandardAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(EntryStandardAnswer record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EntryStandardAnswer record);

    /**
     *
     * @mbg.generated
     */
    List<EntryStandardAnswer> selectByExample(EntryStandardAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    EntryStandardAnswer selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EntryStandardAnswer record, @Param("example") EntryStandardAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EntryStandardAnswer record, @Param("example") EntryStandardAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EntryStandardAnswer record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EntryStandardAnswer record);

    EntryStandardAnswerVO selectEntryStandardAnswerVOByExamQuestionsId(Integer examQuestionsId);
}