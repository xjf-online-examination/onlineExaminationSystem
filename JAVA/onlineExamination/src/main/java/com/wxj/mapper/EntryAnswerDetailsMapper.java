package com.wxj.mapper;

import com.wxj.model.PO.EntryAnswerDetails;
import com.wxj.model.PO.EntryAnswerDetailsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntryAnswerDetailsMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(EntryAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(EntryAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(EntryAnswerDetails record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EntryAnswerDetails record);

    /**
     *
     * @mbg.generated
     */
    List<EntryAnswerDetails> selectByExample(EntryAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    EntryAnswerDetails selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EntryAnswerDetails record, @Param("example") EntryAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EntryAnswerDetails record, @Param("example") EntryAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EntryAnswerDetails record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EntryAnswerDetails record);
}