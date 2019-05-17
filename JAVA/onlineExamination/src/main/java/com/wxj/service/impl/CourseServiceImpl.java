package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.OperationException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.mapper.ClassCourseMapper;
import com.wxj.mapper.CourseMapper;
import com.wxj.model.DTO.CourseParamsDTO;
import com.wxj.model.PO.ClassCourse;
import com.wxj.model.PO.ClassCourseExample;
import com.wxj.model.PO.Course;
import com.wxj.model.PO.CourseExample;
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
    public int save(CourseParamsDTO courseParamsDTO) {
        Date date = new Date();

        Course course = new Course();
        BeanUtils.copyProperties(courseParamsDTO, course);
        course.setCreateTime(date);
        course.setModifyTime(date);
        course.setDelFlag(SystemConstant.NOUGHT);
        int i = 0;
        try {
            i = courseMapper.insertSelective(course);
            if (SystemConstant.ZERO == i) {
                throw new OperationException(" 插入失败");
            }
            ClassCourse classCourse = new ClassCourse();
            classCourse.setClassId(courseParamsDTO.getClassId());
            classCourse.setCourseId(course.getId());
            classCourse.setCreateTime(date);
            classCourse.setModifyTime(date);
            classCourse.setDelFlag(SystemConstant.NOUGHT);
            i = classCourseMapper.insertSelective(classCourse);
            if (SystemConstant.ZERO == i) {
                throw new OperationException(" 插入失败");
            }
        } catch (DuplicateKeyException e) {
            CourseExample classExample = new CourseExample();
            classExample.createCriteria().andCodeEqualTo(courseParamsDTO.getCode());
            Long size = courseMapper.countByExample(classExample);
            if (size > 0) {
                throw new ParamInvalidException("code重复");
            }
        }
        return i;
    }

    @Override
    public int modify(CourseParamsDTO courseParamsDTO) {
        CourseExample classExample = new CourseExample();
        classExample.createCriteria().andIdNotEqualTo(courseParamsDTO.getId()).andCodeEqualTo(courseParamsDTO.getCode());
        Long size = courseMapper.countByExample(classExample);
        if (size > 0) {
            throw new ParamInvalidException("code重复");
        }
        Course course = new Course();

        BeanUtils.copyProperties(courseParamsDTO, course);
        course.setModifyTime(new Date());
        int i = courseMapper.updateByPrimaryKeySelective(course);
        if (SystemConstant.ZERO == i) {
            throw new OperationException("修改失败");
        }
        ClassCourse classCourse = new ClassCourse();
        classCourse.setClassId(courseParamsDTO.getClassId());
        classCourse.setCourseId(course.getId());
        classCourse.setModifyTime(new Date());
        ClassCourseExample classCourseExample = new ClassCourseExample();
        classCourseExample.createCriteria().andClassIdEqualTo(courseParamsDTO.getClassId()).andCourseIdEqualTo(courseParamsDTO.getId()).andModifyTimeEqualTo(new Date());
        i = classCourseMapper.updateByExampleSelective(classCourse, classCourseExample);
        if (SystemConstant.ZERO == i) {
            throw new ParamInvalidException("修改重复");
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
}