package com.wxj.mapper;

import com.wxj.model.PO.StudentAnswer;
import com.wxj.model.PO.StudentAnswerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentAnswerMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(StudentAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(StudentAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(StudentAnswer record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StudentAnswer record);

    /**
     *
     * @mbg.generated
     */
    List<StudentAnswer> selectByExample(StudentAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    StudentAnswer selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StudentAnswer record, @Param("example") StudentAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StudentAnswer record, @Param("example") StudentAnswerExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StudentAnswer record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StudentAnswer record);
}