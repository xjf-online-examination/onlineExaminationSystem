package com.wxj.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.model.DTO.ExamScheduleParamsDTO;
import com.wxj.model.PO.ExamSchedule;
import com.wxj.model.PO.ExamScheduleExample;
import com.wxj.model.VO.ExamScheduleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamScheduleMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(ExamScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(ExamScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(ExamSchedule record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ExamSchedule record);

    /**
     *
     * @mbg.generated
     */
    List<ExamSchedule> selectByExample(ExamScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    ExamSchedule selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ExamSchedule record, @Param("example") ExamScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ExamSchedule record, @Param("example") ExamScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ExamSchedule record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ExamSchedule record);

    List<ExamScheduleVO> listExamScheduleByParams(@Param("record") ExamScheduleParamsDTO examScheduleParamsDTO, PageBounds pageBounds);

    Long countExamScheduleByParams(@Param("record") ExamScheduleParamsDTO examScheduleParamsDTO);
}