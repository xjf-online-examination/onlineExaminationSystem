package com.wxj.service;

import com.wxj.model.DTO.CourseParamsDTO;
import com.wxj.model.VO.ClassCourseSelectVO;
import com.wxj.model.VO.CourseVO;

import java.util.List;

/**
 * <p>Title: CourseServiceI</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-12 22:50
 */
public interface CourseServiceI {

    List<CourseVO> listCourseByParams(CourseParamsDTO courseParamsDTO);

    Long countCourseByParams(CourseParamsDTO courseParamsDTO);

    int save(CourseParamsDTO courseParamsDTO);

    int modify(CourseParamsDTO courseParamsDTO);

    int delete(Integer id);

    /**
     * 所有课程ID，className, courseName供新增教师授课下拉菜单用
     * @return
     */
    List<ClassCourseSelectVO> listClassCourseAll();

    /**
     * 所有课程ID，courseCode, courseName供试题和试卷新增下拉菜单用
     * @return
     */
    List<CourseVO> listCourseAll();
}