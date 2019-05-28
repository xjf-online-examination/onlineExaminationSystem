package com.wxj.mapper;

import com.wxj.model.PO.EntryStandardAnswerDetails;
import com.wxj.model.PO.EntryStandardAnswerDetailsExample;
import com.wxj.model.VO.EntryStandardAnswerDetailsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EntryStandardAnswerDetailsMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(EntryStandardAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(EntryStandardAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(EntryStandardAnswerDetails record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EntryStandardAnswerDetails record);

    /**
     *
     * @mbg.generated
     */
    List<EntryStandardAnswerDetails> selectByExample(EntryStandardAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    EntryStandardAnswerDetails selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") EntryStandardAnswerDetails record, @Param("example") EntryStandardAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") EntryStandardAnswerDetails record, @Param("example") EntryStandardAnswerDetailsExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EntryStandardAnswerDetails record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EntryStandardAnswerDetails record);

    int batchInsert(@Param("list") List<EntryStandardAnswerDetails> list);

    List<EntryStandardAnswerDetailsVO> selectEntryStandardAnswerDetailsVOByEntryStandardAnswerId(@Param("entryAnswerId") Integer entryAnswerId);
}