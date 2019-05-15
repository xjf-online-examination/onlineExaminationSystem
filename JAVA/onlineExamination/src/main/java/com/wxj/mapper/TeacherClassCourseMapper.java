package com.wxj.mapper;

import com.wxj.model.PO.TeacherClassCourse;
import com.wxj.model.PO.TeacherClassCourseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherClassCourseMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(TeacherClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(TeacherClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(TeacherClassCourse record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(TeacherClassCourse record);

    /**
     *
     * @mbg.generated
     */
    List<TeacherClassCourse> selectByExample(TeacherClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    TeacherClassCourse selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TeacherClassCourse record, @Param("example") TeacherClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TeacherClassCourse record, @Param("example") TeacherClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TeacherClassCourse record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TeacherClassCourse record);
}