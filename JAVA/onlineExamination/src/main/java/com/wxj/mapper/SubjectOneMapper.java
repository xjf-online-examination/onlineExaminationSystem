package com.wxj.mapper;

import com.wxj.model.PO.SubjectOne;
import com.wxj.model.PO.SubjectOneExample;
import com.wxj.model.VO.SubjectOneVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectOneMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(SubjectOneExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(SubjectOneExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(SubjectOne record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SubjectOne record);

    /**
     *
     * @mbg.generated
     */
    List<SubjectOne> selectByExample(SubjectOneExample example);

    /**
     *
     * @mbg.generated
     */
    SubjectOne selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SubjectOne record, @Param("example") SubjectOneExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SubjectOne record, @Param("example") SubjectOneExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SubjectOne record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SubjectOne record);

    /**
     * 查询一级科目列表(供新增试题使用)
     * @return
     */
    List<SubjectOneVO> selectSubjectOne();
}