package com.wxj.mapper;

import com.wxj.model.PO.EntryAnswer;
import com.wxj.model.PO.EntryAnswerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntryAnswerMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(EntryAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(EntryAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(EntryAnswer record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EntryAnswer record);

    /**
     *
     * @mbg.generated
     */
    List<EntryAnswer> selectByExample(EntryAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    EntryAnswer selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EntryAnswer record, @Param("example") EntryAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EntryAnswer record, @Param("example") EntryAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EntryAnswer record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EntryAnswer record);
}