package com.wxj.mapper;

import com.wxj.model.PO.StudentSchedule;
import com.wxj.model.PO.StudentScheduleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentScheduleMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(StudentScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(StudentScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(StudentSchedule record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(StudentSchedule record);

    /**
     *
     * @mbg.generated
     */
    List<StudentSchedule> selectByExample(StudentScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    StudentSchedule selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StudentSchedule record, @Param("example") StudentScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StudentSchedule record, @Param("example") StudentScheduleExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StudentSchedule record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StudentSchedule record);
}