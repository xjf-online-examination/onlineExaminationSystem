package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.mapper.ClassCourseMapper;
import com.wxj.mapper.CourseMapper;
import com.wxj.model.DTO.CourseParamsDTO;
import com.wxj.model.DTO.CourseSaveDTO;
import com.wxj.model.PO.ClassCourse;
import com.wxj.model.PO.ClassCourseExample;
import com.wxj.model.PO.Course;
import com.wxj.model.PO.CourseExample;
import com.wxj.model.VO.ClassCourseSelectVO;
import com.wxj.model.VO.CourseVO;
import com.wxj.service.CourseServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: CourseServiceImpl</p >
 * <p>Description: </p >
 * <p>Copyright: Copyright (c) 2018</p >
 * <p>Company: wxj</p >
 *
 * @author wangxiaojun
 * @version 1.0
 * @date 2019-05-12 22:52
 */
@Service
public class CourseServiceImpl implements CourseServiceI {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    ClassCourseMapper classCourseMapper;

    @Override
    public List<CourseVO> listCourseByParams(CourseParamsDTO courseParamsDTO) {
        PageBounds pageBounds = new PageBounds(courseParamsDTO.getCurrentPage(), courseParamsDTO.getPageSize());
        return courseMapper.selectCourseByParams(courseParamsDTO, pageBounds);
    }

    @Override
    public Long countCourseByParams(CourseParamsDTO courseParamsDTO) {
        return courseMapper.countCourseByParams(courseParamsDTO);
    }

    @Transactional
    @Override
    public int save(CourseSaveDTO courseSaveDTO) {
        Date date = new Date();

        Course course = new Course();
        BeanUtils.copyProperties(courseSaveDTO, course);
        course.setCreateTime(date);
        course.setModifyTime(date);
        course.setDelFlag(SystemConstant.NOUGHT);
        int i = 0;
        try {
            i = courseMapper.insertSelective(course);
            if (SystemConstant.ZERO == i) {
                throw new OperationException(" 插入失败");
            }
            for (Integer classId : courseSaveDTO.getClassIdList()) {
                ClassCourse classCourse = new ClassCourse();
                classCourse.setClassId(classId);
                classCourse.setCourseId(course.getId());
                classCourse.setCreateTime(date);
                classCourse.setModifyTime(date);
                classCourse.setDelFlag(SystemConstant.NOUGHT);
                i = classCourseMapper.insertSelective(classCourse);
                if (SystemConstant.ZERO == i) {
                    throw new OperationException(" 插入失败");
                }
            }
        } catch (DuplicateKeyException e) {
            CourseExample classExample = new CourseExample();
            classExample.createCriteria().andCodeEqualTo(courseSaveDTO.getCode());
            Long size = courseMapper.countByExample(classExample);
            if (size > 0) {
                throw new ParamInvalidException("code重复");
            }
        }
        return i;
    }

    @Override
    public int modify(CourseSaveDTO courseSaveDTO) {
        Date date = new Date();
        CourseExample classExample = new CourseExample();
        classExample.createCriteria().andIdNotEqualTo(courseSaveDTO.getId()).andCodeEqualTo(courseSaveDTO.getCode());
        Long size = courseMapper.countByExample(classExample);
        if (size > 0) {
            throw new ParamInvalidException("code重复");
        }
        Course course = new Course();

        BeanUtils.copyProperties(courseSaveDTO, course);
        course.setModifyTime(new Date());
        int i = courseMapper.updateByPrimaryKeySelective(course);
        if (SystemConstant.ZERO == i) {
            throw new OperationException("修改失败");
        }

        //删除班级与课程的对应关系
        ClassCourseExample classCourseExample = new ClassCourseExample();
        classCourseExample.createCriteria().andCourseIdEqualTo(courseSaveDTO.getId());
        classCourseMapper.deleteByExample(classCourseExample);
        //重新插入班级课程对应表
        for (Integer classId : courseSaveDTO.getClassIdList()) {
            ClassCourse classCourse = new ClassCourse();
            classCourse.setClassId(classId);
            classCourse.setCourseId(course.getId());
            classCourse.setCreateTime(date);
            classCourse.setModifyTime(date);
            classCourse.setDelFlag(SystemConstant.NOUGHT);
            i = classCourseMapper.insertSelective(classCourse);
        }
        if (SystemConstant.ZERO == i) {
            throw new ParamInvalidException("修改失败");
        }

        return i;
    }

    @Override
    public int delete(Integer id) {
        //TODO:
        //删除课程
        //删除课程和班级对应表
        //删除课程的试题
        //删除这个课程的试卷
        //删除这个试卷的考试安排
        return 0;
    }

    @Override
    public List<ClassCourseSelectVO> listClassCourseAll() {
        return classCourseMapper.selectClassCourseAll();
    }

    @Override
    public List<CourseVO> listCourseAll() {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria();
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        List<CourseVO> courseVOList = Lists.newArrayList();
        CourseVO courseVO;
        for (Course course : courseList) {
            courseVO = new CourseVO();
            courseVO.setId(course.getId());
            courseVO.setCode(course.getCode());
            courseVO.setName(course.getName());
            courseVOList.add(courseVO);
        }
        return courseVOList;
    }

}