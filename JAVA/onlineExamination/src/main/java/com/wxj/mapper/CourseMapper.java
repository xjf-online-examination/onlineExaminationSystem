package com.wxj.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.model.DTO.CourseParamsDTO;
import com.wxj.model.PO.Course;
import com.wxj.model.PO.CourseExample;
import com.wxj.model.VO.CourseVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(CourseExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(CourseExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(Course record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Course record);

    /**
     *
     * @mbg.generated
     */
    List<Course> selectByExample(CourseExample example);

    /**
     *
     * @mbg.generated
     */
    Course selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Course record);

    List<CourseVO> selectCourseByParams(@Param("record")CourseParamsDTO courseParamsDTO, PageBounds pageBounds);

    Long countCourseByParams(@Param("record")CourseParamsDTO courseParamsDTO);
}