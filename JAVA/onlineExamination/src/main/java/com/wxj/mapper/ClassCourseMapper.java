package com.wxj.mapper;

import com.wxj.model.PO.ClassCourse;
import com.wxj.model.PO.ClassCourseExample;
import com.wxj.model.VO.ClassCourseSelectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassCourseMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(ClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(ClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(ClassCourse record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ClassCourse record);

    /**
     *
     * @mbg.generated
     */
    List<ClassCourse> selectByExample(ClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    ClassCourse selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") ClassCourse record, @Param("example") ClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") ClassCourse record, @Param("example") ClassCourseExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ClassCourse record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ClassCourse record);

    List<ClassCourseSelectVO> selectClassCourseAll();
}