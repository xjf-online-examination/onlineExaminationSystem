package com.wxj.service.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.wxj.constant.SystemConstant;
import com.wxj.exception.BusinessRuntimeException;
import com.wxj.exception.OperationException;
import com.wxj.exception.ParamInvalidException;
import com.wxj.mapper.*;
import com.wxj.model.DTO.CourseParamsDTO;
import com.wxj.model.DTO.CourseSaveDTO;
import com.wxj.model.PO.*;
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
import java.util.stream.Collectors;

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
    @Autowired
    ExamQuestionsMapper examQuestionsMapper;
    @Autowired
    ExamPaperQuestionsMapper examPaperQuestionsMapper;
    @Autowired
    ExamPaperMapper examPaperMapper;
    @Autowired
    ExamScheduleMapper examScheduleMapper;
    @Autowired
    StudentAnswerMapper studentAnswerMapper;
    @Autowired
    EntryAnswerDetailsMapper entryAnswerDetailsMapper;

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

    @Transactional
    @Override
    public void delete(Integer id) {
        //提示：删除课程会课程对应的试题，试卷，考试信息，考试成绩。确认删除？
        //删除课程
        Course course = courseMapper.selectByPrimaryKey(id);
        if (null == course) {
            throw new OperationException("删除失败,没有这门课程");
        }
        try {
            courseMapper.deleteByPrimaryKey(id);
            //删除课程和班级对应表
            ClassCourseExample classCourseExample = new ClassCourseExample();
            classCourseExample.createCriteria().andCourseIdEqualTo(id);
            classCourseMapper.deleteByExample(classCourseExample);
            //删除课程的试题
            ExamQuestionsExample examQuestionsExample = new ExamQuestionsExample();
            examQuestionsExample.createCriteria().andCourseCodeEqualTo(course.getCode());
            examQuestionsMapper.deleteByExample(examQuestionsExample);
            //删除试卷与试题对应关系
            ExamPaperExample examPaperExample = new ExamPaperExample();
            examPaperExample.createCriteria().andCourseCodeEqualTo(course.getCode());
            List<Integer> examPaperIdList = examPaperMapper.selectByExample(examPaperExample).stream().map(ExamPaper::getId).collect(Collectors.toList());
            if (null == examPaperIdList && examPaperIdList.size() == 0) {
                ExamPaperQuestionsExample examPaperQuestionsExample = new ExamPaperQuestionsExample();
                examPaperQuestionsExample.createCriteria().andExamPaperIdIn(examPaperIdList);
                examPaperQuestionsMapper.deleteByExample(examPaperQuestionsExample);
                //删除这个课程的试卷
                examPaperMapper.deleteByExample(examPaperExample);
                //删除这个试卷的考试安排
                ExamScheduleExample examScheduleExample = new ExamScheduleExample();
                examScheduleExample.createCriteria().andExamPaperIdIn(examPaperIdList);
                List<Integer> examScheduleIdList = examScheduleMapper.selectByExample(examScheduleExample).stream().map(ExamSchedule::getId).collect(Collectors.toList());
                if (null != examScheduleIdList && examScheduleIdList.size() > 0) {
                    examScheduleMapper.deleteByExample(examScheduleExample);
                    //删除考生答案
                    StudentAnswerExample studentAnswerExample = new StudentAnswerExample();
                    studentAnswerExample.createCriteria().andExamScheduleIdIn(examScheduleIdList);
                    List<Integer> studentAnswerIdList = studentAnswerMapper.selectByExample(studentAnswerExample).stream().map(StudentAnswer::getId).collect(Collectors.toList());
                    studentAnswerMapper.deleteByExample(studentAnswerExample);
                    if (null != studentAnswerIdList && studentAnswerIdList.size() > 0) {
                        EntryAnswerDetailsExample entryAnswerDetailsExample = new EntryAnswerDetailsExample();
                        entryAnswerDetailsExample.createCriteria().andEntryAnswerIdIn(studentAnswerIdList);
                        entryAnswerDetailsMapper.deleteByExample(entryAnswerDetailsExample);
                    }
                }
            }
        } catch (BusinessRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new OperationException("删除失败");
        }
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